package app.darkharov.test.task.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.darkharov.test.task.domain.ItemRepository
import app.darkharov.test.task.list.elements.item.ItemsToListPropsMapper
import app.darkharov.test.task.list.elements.item.ListCallbacks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
internal class ListViewModel @Inject constructor(
    private val repository: ItemRepository,
) : ViewModel(),
    ListCallbacks {

    private val events = Channel<Event>(capacity = Channel.UNLIMITED)

    val props: StateFlow<ListProps> =
        repository
            .all()
            .map { ItemsToListPropsMapper.transform(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ListProps.Loading,
            )

    override fun onClick(itemId: Int) {
        events.trySend(Event.OnClicked(itemId = itemId))
    }

    override fun onCheckedChange(itemId: Int, newValue: Boolean) {
        viewModelScope.launch {
            repository.setChecked(id = itemId, checked = newValue)
        }
    }

    override fun onCheckAllClick(newValue: Boolean) {
        viewModelScope.launch {
            repository.setAllChecked(checked = newValue)
        }
    }

    fun events(): ReceiveChannel<Event> =
        events

    sealed class Event {
        data class OnClicked(val itemId: Int) : Event()
    }
}
