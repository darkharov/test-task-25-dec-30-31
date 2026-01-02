package app.darkharov.test.task.details

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.darkharov.test.task.commons.compose.elements.button.AppButtonStateProps
import app.darkharov.test.task.domain.Item
import app.darkharov.test.task.domain.ItemRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

@HiltViewModel(
    assistedFactory = DetailsViewModel.Factory::class,
)
class DetailsViewModel @AssistedInject constructor(
    @Assisted
    private val itemId: Int?,
    private val repository: ItemRepository,
) : ViewModel(),
    DetailsProps,
    DetailsCallbacks {

    private val events = Channel<Event>(capacity = Channel.UNLIMITED)

    override val title = TextFieldState()
    override var checked by mutableStateOf(false)
    override val saveButtonState by derivedStateOf { computeButtonState() }
    override var saving by mutableStateOf(false)

    private var initialValue by mutableStateOf<Item?>(null)
    private val filledOutValue by derivedStateOf {
        Item(
            id = itemId ?: 0,
            title = title.text.toString(),
            checked = checked,
        )
    }

    private fun computeButtonState(): AppButtonStateProps = when {
        saving -> AppButtonStateProps.Progress
        initialValue == filledOutValue -> AppButtonStateProps.Disabled
        else -> AppButtonStateProps.Normal
    }

    init {
        if (itemId != null) {
            viewModelScope.launch {
                val item = repository.get(id = itemId)
                title.setTextAndPlaceCursorAtEnd(item.title)
                checked = item.checked
                initialValue = item
            }
        }
    }

    override fun onCheckedChange(newValue: Boolean) {
        checked = newValue
    }

    override fun onSave() {
        viewModelScope.launch {
            saving = true
            repository.put(filledOutValue)
            events.trySend(Event.OnNewValueSaved)
        }
    }

    fun events(): ReceiveChannel<Event> =
        events

    sealed class Event {
        data object OnNewValueSaved : Event()
    }

    @AssistedFactory
    fun interface Factory {
        fun create(itemId: Int?): DetailsViewModel
    }
}
