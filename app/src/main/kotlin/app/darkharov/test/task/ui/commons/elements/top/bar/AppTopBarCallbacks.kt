package app.darkharov.test.task.ui.commons.elements.top.bar

interface AppTopBarCallbacks {
    fun onUpClick()
    fun onLogOutClick()
}

object AppTopBarCallbacksMock : AppTopBarCallbacks {
    override fun onUpClick() {}
    override fun onLogOutClick() {}
}
