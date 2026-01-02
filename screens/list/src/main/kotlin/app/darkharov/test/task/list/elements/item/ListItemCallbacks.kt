package app.darkharov.test.task.list.elements.item

import androidx.compose.runtime.Stable

@Stable
internal interface ListItemCallbacks {
    fun onClick(itemId: Int)
    fun onCheckedChange(itemId: Int, newValue: Boolean)
}

internal object ListItemCallbacksMock : ListItemCallbacks {
    override fun onClick(itemId: Int) {}
    override fun onCheckedChange(itemId: Int, newValue: Boolean) {}
}
