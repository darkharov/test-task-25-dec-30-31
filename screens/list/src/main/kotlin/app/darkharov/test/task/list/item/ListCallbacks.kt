package app.darkharov.test.task.list.item

import androidx.compose.runtime.Stable

@Stable
internal interface ListCallbacks : ListItemCallbacks

internal object ListCallbacksMock :
    ListCallbacks,
    ListItemCallbacks by ListItemCallbacksMock
