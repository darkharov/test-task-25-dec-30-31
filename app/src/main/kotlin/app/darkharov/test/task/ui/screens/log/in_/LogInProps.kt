package app.darkharov.test.task.ui.screens.log.in_

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Stable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.darkharov.test.task.ui.commons.elements.button.AppButtonStateProps

@Stable
interface LogInProps {
    val login: TextFieldState
    val loginError: Boolean
    val password: TextFieldState
    val passwordError: Boolean
    val fieldsEnabled: Boolean
    val formValid: Boolean
    val logInButtonState: AppButtonStateProps
}

class LogInMocks : PreviewParameterProvider<LogInProps> {

    override val values = sequenceOf<LogInProps>(
        Prototype(),
        Prototype(
            login = TextFieldState("wrong#name"),
            loginError = true,
            formValid = false,
        ),
        Prototype(
            login = TextFieldState("valid_name"),
            password = TextFieldState("11110000"),
            fieldsEnabled = false,
            logInButtonState = AppButtonStateProps.Progress,
        ),
    )

    private data class Prototype(
        override val login: TextFieldState = TextFieldState(),
        override val loginError: Boolean = false,
        override val password: TextFieldState = TextFieldState(),
        override val passwordError: Boolean = false,
        override val fieldsEnabled: Boolean = true,
        override val formValid: Boolean = true,
        override val logInButtonState: AppButtonStateProps = AppButtonStateProps.Disabled,
    ) : LogInProps
}
