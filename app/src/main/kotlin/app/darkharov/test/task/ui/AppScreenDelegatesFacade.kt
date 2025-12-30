package app.darkharov.test.task.ui

import androidx.compose.material3.SnackbarHostState
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.darkharov.test.task.ui.screens.log.in_.LogInDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class AppScreenDelegatesFacade(
    private val coroutineScope: CoroutineScope,
    private val snackbarHostState: SnackbarHostState,
    private val backStack: NavBackStack<NavKey>,
) : LogInDelegate {

    override fun showSnackbar(message: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message = message)
        }
    }

    override fun goToListScreen() {
        backStack.remove(Login)
        backStack.add(List)
    }
}
