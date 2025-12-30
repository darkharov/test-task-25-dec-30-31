package app.darkharov.test.task.ui.commons.elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.darkharov.test.task.ui.commons.theme.AppTheme

@Composable
fun AppSupportingText(
    value: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = value,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun AppSupportingTextPreview() {
    AppTheme {
        AppSupportingText(
            value = "Supporting Text",
        )
    }
}
