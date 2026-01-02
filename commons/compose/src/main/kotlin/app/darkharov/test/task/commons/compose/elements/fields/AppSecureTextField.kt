package app.darkharov.test.task.commons.compose.elements.fields

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.darkharov.test.task.commons.compose.elements.AppLabel
import app.darkharov.test.task.commons.compose.elements.AppSupportingText
import app.darkharov.test.task.commons.compose.theme.AppTheme

@Composable
fun AppSecureTextField(
    state: TextFieldState,
    label: String,
    supportingText: String,
    isError: Boolean,
    imeAction: ImeAction,
    onKeyboardAction: KeyboardActionHandler,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    OutlinedSecureTextField(
        state = state,
        modifier = modifier,
        enabled = enabled,
        label = {
            AppLabel(
                value = label,
            )
        },
        supportingText = {
            AppSupportingText(
                value = supportingText,
            )
        },
        isError = isError,
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Password,
            imeAction = imeAction,
        ),
        onKeyboardAction = onKeyboardAction,
    )
}

@Preview
@Composable
private fun AppSecureTextFieldPreview() {
    AppTheme {
        AppSecureTextField(
            state = rememberTextFieldState(),
            label = "Label",
            supportingText = "Supporting Text",
            isError = false,
            imeAction = ImeAction.Done,
            onKeyboardAction = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
