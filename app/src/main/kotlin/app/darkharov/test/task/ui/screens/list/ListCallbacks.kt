package app.darkharov.test.task.ui.screens.list

import androidx.compose.runtime.Stable
import app.darkharov.test.task.ui.screens.list.elements.item.ListItemCallbacks
import app.darkharov.test.task.ui.screens.list.elements.item.ListItemCallbacksMock

@Stable
internal interface ListCallbacks : ListItemCallbacks

internal object ListCallbacksMock :
    ListCallbacks,
    ListItemCallbacks by ListItemCallbacksMock
