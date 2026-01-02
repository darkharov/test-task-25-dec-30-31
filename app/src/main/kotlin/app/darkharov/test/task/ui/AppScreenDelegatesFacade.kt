package app.darkharov.test.task.ui

import androidx.compose.material3.SnackbarHostState
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.darkharov.test.task.commons.compose.elements.top.bar.AppTopBarCallbacks
import app.darkharov.test.task.log.in_.LogInDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class AppScreenDelegatesFacade(
    private val coroutineScope: CoroutineScope,
    private val snackbarHostState: SnackbarHostState,
    private val backStack: NavBackStack<NavKey>,
) : LogInDelegate,
    AppTopBarCallbacks {

    override fun showSnackbar(message: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message = message)
        }
    }

    override fun goToListScreen() {
        backStack.remove(AppScreenKey.Login)
        backStack.add(AppScreenKey.List)
    }

    override fun onUpClick() {
        backStack.removeLastOrNull()
    }

    override fun onLogOutClick() {
        backStack.clear()
        backStack.add(AppScreenKey.Login)
    }
}
