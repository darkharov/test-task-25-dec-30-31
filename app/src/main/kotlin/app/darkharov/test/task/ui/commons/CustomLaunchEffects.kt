package app.darkharov.test.task.ui.commons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.channels.ReceiveChannel

@Composable
@NonRestartableComposable
fun <E> EventConsumer(
    events: ReceiveChannel<E>,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    onEvent: suspend (E) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(events, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(lifecycleState) {
            for (event in events) {
                onEvent(event)
            }
        }
    }
}
