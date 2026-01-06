package app.darkharov.test.task.data

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
internal class ItemRepositoryImpl @Inject constructor() : ItemRepository {

    private val mutex = Mutex()
    private val idsItems = createIdsItems()
    private val itemsFlow = MutableSharedFlow<List<Item>>(replay = 1)

    init {
        emitItemList()
    }

    private fun createIdsItems() =
        List(30) { index ->
            Item(
                id = index + 1,
                title = "Item ${index + 1}",
                checked = false,
            )
        }.associateBy { it.id }
            .toMutableMap()

    private fun emitItemList() {
        val newList = idsItems.values.sortedBy { it.id }
        itemsFlow.tryEmit(newList)
    }

    override fun all(): Flow<List<Item>> =
        itemsFlow
            .onStart { delay(1.seconds) }   // to simulate initial loading

    override suspend fun get(id: Int): Item =
        mutex.withLock {
            idsItems.getValue(id)
        }

    override suspend fun setChecked(id: Int, checked: Boolean) {
        mutex.withLock {
            idsItems[id] = idsItems.getValue(id).copy(checked = checked)
            emitItemList()
        }
    }

    override suspend fun setAllChecked(checked: Boolean) {
        mutex.withLock {
            idsItems
                .iterator()
                .forEach { entry ->
                    entry.setValue(entry.value.copy(checked = checked))
                }
            emitItemList()
        }
    }

    override suspend fun put(item: Item) {
        mutex.withLock {
            idsItems[item.id] = item
            emitItemList()
        }
        delay(1.seconds)    // to simulate long work
    }
}
