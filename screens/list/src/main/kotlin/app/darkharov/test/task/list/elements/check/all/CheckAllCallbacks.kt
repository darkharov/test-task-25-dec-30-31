package app.darkharov.test.task.list.elements.check.all

import androidx.compose.runtime.Stable

@Stable
internal interface CheckAllCallbacks {
    fun onCheckAllClick(newValue: Boolean)
}

internal object CheckAllCallbacksMock : CheckAllCallbacks {
    override fun onCheckAllClick(newValue: Boolean) {}
}
