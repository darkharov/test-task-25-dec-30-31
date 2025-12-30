package app.darkharov.test.task.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import app.darkharov.test.task.ui.commons.elements.top.bar.AppTopBar
import app.darkharov.test.task.ui.screens.list.ListScreen
import app.darkharov.test.task.ui.screens.log.in_.LogInScreen
import kotlinx.serialization.Serializable

@Serializable
data object Login : NavKey

@Serializable
data object List : NavKey

@Composable
internal fun AppContent() {
    val backStack = rememberNavBackStack(Login)
    val navIconVisible by remember { derivedStateOf { backStack.isEmpty() } }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            AppTopBar(
                navIconVisible = navIconVisible,
                onNavIconClick = {
                    backStack.removeLastOrNull()
                },
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = {
                    Snackbar(
                        it,
                        modifier = Modifier
                            .windowInsetsPadding(WindowInsets.navigationBars),
                    )
                },
            )
        },
        contentWindowInsets = WindowInsets
            .statusBars
            .union(
                WindowInsets
                    .displayCutout
                    .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
            )
            .union(WindowInsets.ime),
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier
                .padding(innerPadding),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
                val delegates = AppScreenDelegatesFacade(
                    backStack = backStack,
                    coroutineScope = coroutineScope,
                    snackbarHostState = snackbarHostState,
                )
                entry<Login> {
                    LogInScreen(
                        delegate = delegates,
                    )
                }
                entry<List> {
                    ListScreen()
                }
            },
        )
    }
}
