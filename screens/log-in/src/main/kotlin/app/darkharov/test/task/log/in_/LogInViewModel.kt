package app.darkharov.test.task.log.in_

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.darkharov.test.task.commons.compose.elements.button.AppButtonStateProps
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

    override val login = FieldPropsImpl(Regex("^([_\\p{Alpha}]{6,16})$"))
    override val password = FieldPropsImpl(Regex("^([_\\d]{8,12})$"))
    override val fieldsEnabled by derivedStateOf { !(loggingIn) }
    override val formValid by derivedStateOf { login.valid && password.valid }
    override val logInButtonState by derivedStateOf { computeLogInButtonState() }

    private val correctLogin = "qwerty"
    private val correctPassword = "11112222"
    private val events = Channel<Event>(capacity = Channel.UNLIMITED)
    private var loggingIn by mutableStateOf(false)
    private val allFields = listOf(login, password)

    private fun computeLogInButtonState(): AppButtonStateProps =
        if (loggingIn) {
            AppButtonStateProps.Progress
        } else {
            AppButtonStateProps.enabledIf(formValid)
        }

    override fun onLoginUnfocused() {
        login.notifyUnfocused()
    }

    override fun onPasswordUnfocused() {
        password.notifyUnfocused()
    }

    override fun onAttemptToComplete() {
        if (allFields.all { it.valid }) {
            logIn(
                login = login.value.text.toString(),
                password = password.value.text.toString(),
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

    inner class FieldPropsImpl(
        private val regex: Regex,
    ) : LogInProps.FieldProps {

        override val value = TextFieldState()
        override val error by derivedStateOf { indicateIfNotValid && !(valid) }
        val valid get() = value.text.matches(regex)
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
