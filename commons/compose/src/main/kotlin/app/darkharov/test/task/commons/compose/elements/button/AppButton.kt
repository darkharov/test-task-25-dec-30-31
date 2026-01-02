package app.darkharov.test.task.commons.compose.elements.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import app.darkharov.test.task.commons.compose.theme.AppTheme

private val ProgressIndicatorSize = 16.dp

@Composable
fun AppButton(
    text: String,
    state: AppButtonStateProps = AppButtonStateProps.Normal,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = {
            onClick()
        },
        enabled = state == AppButtonStateProps.Normal,
        modifier = modifier,
    ) {
        ProgressIndicatorPlaceholder()
        Text(
            text = text,
            style = MaterialTheme
                .typography
                .labelLarge
                .copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
            modifier = Modifier
                .padding(
                    horizontal = 12.dp,
                )
        )
        if (state == AppButtonStateProps.Progress) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.tertiary,
                strokeWidth = 2.dp,
                modifier = Modifier
                    .size(ProgressIndicatorSize),
            )
        } else {
            ProgressIndicatorPlaceholder()
        }
    }
}

@Composable
private fun ProgressIndicatorPlaceholder() {
    Spacer(
        modifier = Modifier
            .size(ProgressIndicatorSize),
    )
}

@Composable
@PreviewLightDark
private fun AppButtonPreview(
    @PreviewParameter(
        provider = AppButtonStateMocks::class,
    )
    state: AppButtonStateProps,
) {
    AppTheme {
        AppButton(
            text = "Text",
            state = state,
            onClick = {},
        )
    }
}
