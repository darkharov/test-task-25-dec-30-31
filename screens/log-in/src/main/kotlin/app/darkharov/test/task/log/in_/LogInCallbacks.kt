package app.darkharov.test.task.log.in_

internal interface LogInCallbacks {
    fun onLoginUnfocused()
    fun onPasswordUnfocused()
    fun onAttemptToComplete()
}

internal object LogInCallbacksMock : LogInCallbacks {
    override fun onLoginUnfocused() {}
    override fun onPasswordUnfocused() {}
    override fun onAttemptToComplete() {}
}
