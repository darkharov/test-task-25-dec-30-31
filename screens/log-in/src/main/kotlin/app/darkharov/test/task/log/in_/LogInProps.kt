package app.darkharov.test.task.log.in_

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Stable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.darkharov.test.task.commons.compose.elements.button.AppButtonStateProps

@Stable
interface LogInProps {

    val login: FieldProps
    val password: FieldProps
    val fieldsEnabled: Boolean
    val formValid: Boolean
    val logInButtonState: AppButtonStateProps

    interface FieldProps {
        val value: TextFieldState
        val error: Boolean
    }
}

class LogInMocks : PreviewParameterProvider<LogInProps> {

    override val values = sequenceOf<LogInProps>(
        Prototype(),
        Prototype(
            login = FieldPropsPrototype(
                value = TextFieldState("wrong#name"),
                error = true,
            ),
            formValid = false,
        ),
        Prototype(
            login = FieldPropsPrototype(
                value = TextFieldState("valid_name"),
            ),
            password = FieldPropsPrototype(
                value = TextFieldState("11110000"),
            ),
            fieldsEnabled = false,
            logInButtonState = AppButtonStateProps.Progress,
        ),
    )

    private class Prototype(
        override val login: LogInProps.FieldProps = FieldPropsPrototype(),
        override val password: LogInProps.FieldProps = FieldPropsPrototype(),
        override val fieldsEnabled: Boolean = true,
        override val formValid: Boolean = true,
        override val logInButtonState: AppButtonStateProps = AppButtonStateProps.Disabled,
    ) : LogInProps

    private data class FieldPropsPrototype(
        override val value: TextFieldState = TextFieldState(),
        override val error: Boolean = false,
    ) : LogInProps.FieldProps
}
