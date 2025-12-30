package app.darkharov.test.task.ui.screens.log.in_

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.darkharov.test.task.ui.commons.elements.button.AppButtonStateProps
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
internal class LogInViewModel @Inject constructor() :
    ViewModel(),
    LogInProps,
    LogInCallbacks {

    private val correctLogin = "qwerty"
    private val correctPassword = "11112222"

    private val events = Channel<Event>(capacity = Channel.UNLIMITED)

    private val loginField = Field(Regex("^([_\\p{Alpha}]{6,16})$"))
    private val passwordField = Field(Regex("^([_\\d]{8,12})$"))
    private val allFields = listOf(loginField, passwordField)

    override val formValid by derivedStateOf { loginField.valid && passwordField.valid }
    private var loggingIn by mutableStateOf(false)

    override val login get() = loginField.value
    override val loginError get() = loginField.error
    override val password get() = passwordField.value
    override val passwordError get() = passwordField.error
    override val fieldsEnabled by derivedStateOf { !(loggingIn) }
    override val logInButtonState by derivedStateOf { computeLogInButtonState() }

    private fun computeLogInButtonState(): AppButtonStateProps =
        if (loggingIn) {
            AppButtonStateProps.Progress
        } else {
            AppButtonStateProps.enabledIf(formValid)
        }

    override fun onLoginUnfocused() {
        loginField.notifyUnfocused()
    }

    override fun onPasswordUnfocused() {
        passwordField.notifyUnfocused()
    }

    override fun onAttemptToComplete() {
        if (allFields.all { it.valid }) {
            logIn(
                login = loginField.value.text.toString(),
                password = passwordField.value.text.toString(),
            )
        } else {
            allFields.forEach { it.indicateErrorIfNeeded() }
        }
    }

    private fun logIn(login: String, password: String) {
        viewModelScope.launch {
            loggingIn = true
            delay(3_000)
            if (
                login == correctLogin &&
                password == correctPassword
            ) {
                events.trySend(Event.OnLoggedIn)
            } else {
                loggingIn = false
                events.trySend(
                    Event.OnLoginFailed(
                        message = "You have entered an invalid name or password",
                    ),
                )
            }
        }
    }

    fun events(): ReceiveChannel<Event> =
        events

    sealed class Event {
        data object OnLoggedIn : Event()
        data class OnLoginFailed(val message: String) : Event()
    }

    private inner class Field(private val regex: Regex) {

        val value = TextFieldState()
        val valid get() = value.text.matches(regex)
        val error by derivedStateOf { indicateIfNotValid && !(valid) }
        private var indicateIfNotValid by mutableStateOf(false)

        fun notifyUnfocused() {
            if (value.text.isNotEmpty()) {
                indicateErrorIfNeeded()
            }
        }

        fun indicateErrorIfNeeded() {
            indicateIfNotValid = true
        }

        init {
            viewModelScope.launch {
                snapshotFlow { value.text.toString() }      // The user is typing, so (IMHO)
                    .collect { indicateIfNotValid = false } // it is better to hide the annoying error
            }
        }
    }
}
