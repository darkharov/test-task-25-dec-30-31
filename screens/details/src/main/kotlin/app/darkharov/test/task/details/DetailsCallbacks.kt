package app.darkharov.test.task.details

import androidx.compose.runtime.Stable

@Stable
internal interface DetailsCallbacks {
    fun onCheckedChange(newValue: Boolean)
    fun onSave()
}

internal object DetailsCallbacksMock : DetailsCallbacks {
    override fun onSave() {}
    override fun onCheckedChange(newValue: Boolean) {}
}
