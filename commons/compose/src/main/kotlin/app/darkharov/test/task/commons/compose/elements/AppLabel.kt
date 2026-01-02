package app.darkharov.test.task.commons.compose.elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.darkharov.test.task.commons.compose.theme.AppTheme

@Composable
fun AppLabel(
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
private fun AppLabelPreview() {
    AppTheme {
        AppLabel(
            value = "Label",
        )
    }
}
