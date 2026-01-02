package app.darkharov.test.task.commons.compose.elements.fields

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import app.darkharov.test.task.commons.compose.elements.AppLabel
import app.darkharov.test.task.commons.compose.elements.AppSupportingText
import app.darkharov.test.task.commons.compose.theme.AppTheme

@Composable
fun AppTextField(
    state: TextFieldState,
    label: String,
    supportingText: String,
    imeAction: ImeAction,
    onKeyboardAction: KeyboardActionHandler,
    isError: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    OutlinedTextField(
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
            imeAction = imeAction,
        ),
        onKeyboardAction = onKeyboardAction,
    )
}

@PreviewLightDark
@Composable
private fun AppTextFieldPreview() {
    AppTheme {
        AppTextField(
            state = rememberTextFieldState(),
            label = "Label",
            supportingText = "Supporting text",
            isError = false,
            imeAction = ImeAction.Done,
            onKeyboardAction = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
