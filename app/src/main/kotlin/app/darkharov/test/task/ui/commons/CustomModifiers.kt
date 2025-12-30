package app.darkharov.test.task.ui.commons

import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged

@Suppress("UnusedReceiverParameter")
fun Modifier.customOnFocusCallbacks(
    onFocused: () -> Unit = {},
    onUnfocused: () -> Unit = {},
) =
    Modifier
        .onFocusChanged {
            if (it.hasFocus) {
                onFocused()
            } else {
                onUnfocused()
            }
        }
