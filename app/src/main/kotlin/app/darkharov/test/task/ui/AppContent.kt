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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import app.darkharov.test.task.R
import app.darkharov.test.task.commons.compose.elements.top.bar.AppTopBar
import app.darkharov.test.task.commons.compose.elements.top.bar.AppTopBarProps
import app.darkharov.test.task.details.DetailsScreen
import app.darkharov.test.task.list.ListScreen
import app.darkharov.test.task.log.in_.LogInScreen

@Composable
internal fun AppContent() {

    val backStack = rememberNavBackStack(AppScreenKey.List)
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val delegates = remember {
        AppScreenDelegatesFacade(
            backStack = backStack,
            coroutineScope = coroutineScope,
            snackbarHostState = snackbarHostState,
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            AppTopBar(
                props = AppTopBarProps(
                    title = stringResource(
                        (backStack.lastOrNull() as AppScreenKey?)
                            ?.toolbarTitleId
                            ?: R.string._empty
                    ),
                    upIconVisible = backStack.size > 1,
                    logOutIconVisible = (backStack.lastOrNull() as AppScreenKey?)
                        ?.requiresLogin
                        ?: false,
                ),
                callbacks = delegates,
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData = snackbarData,
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
                entry<AppScreenKey.Login> {
                    LogInScreen(
                        delegate = delegates,
                    )
                }
                entry<AppScreenKey.List> {
                    ListScreen(
                        delegate = delegates,
                    )
                }
                entry<AppScreenKey.Details> { key ->
                    DetailsScreen(
                        itemId = key.itemId,
                        delegate = delegates,
                    )
                }
            },
        )
    }
}
