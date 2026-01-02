package app.darkharov.test.task.temp

import app.darkharov.test.task.domain.Item
import app.darkharov.test.task.domain.ItemRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@Singleton
class ItemRepositoryMock @Inject constructor() : ItemRepository {

    private val mutex = Mutex()
    private val idItems = dataSet()
    private val itemsFlow = MutableSharedFlow<List<Item>>(replay = 1)

    init {
        emitNewDataSet()
    }

    override fun all(): Flow<List<Item>> =
        itemsFlow
            .onStart { delay(1.seconds) }   // to simulate initial loading

    override suspend fun get(id: Int): Item =
        mutex.withLock {
            idItems.getValue(id)
        }

    override suspend fun setChecked(id: Int, checked: Boolean) {
        mutex.withLock {
            idItems[id] = idItems.getValue(id).copy(checked = checked)
            emitNewDataSet()
        }
    }

    private fun emitNewDataSet() {
        val newList = idItems.values.sortedBy { it.id }
        itemsFlow.tryEmit(newList)
    }

    override suspend fun setAllChecked(checked: Boolean) {
        mutex.withLock {
            idItems
                .iterator()
                .forEach { entry ->
                    entry.setValue(entry.value.copy(checked = checked))
                }
            emitNewDataSet()
        }
    }

    private fun dataSet() =
        List(30) { index ->
            Item(
                id = index + 1,
                title = "Item ${index + 1}",
                checked = false,
            )
        }.associateBy { it.id }
            .toMutableMap()

    override suspend fun put(item: Item) {
        mutex.withLock {
            idItems[item.id] = item
            delay(1.seconds)   // to simulate long work
            emitNewDataSet()
        }
    }
}
