package app.darkharov.test.task.list

import androidx.compose.runtime.Stable
import app.darkharov.test.task.list.elements.check.all.CheckAllCallbacks
import app.darkharov.test.task.list.elements.check.all.CheckAllCallbacksMock
import app.darkharov.test.task.list.elements.item.ListItemCallbacks
import app.darkharov.test.task.list.elements.item.ListItemCallbacksMock

@Stable
internal interface ListCallbacks :
    ListItemCallbacks,
    CheckAllCallbacks

internal object ListCallbacksMock :
    ListCallbacks,
    ListItemCallbacks by ListItemCallbacksMock,
    CheckAllCallbacks by CheckAllCallbacksMock
