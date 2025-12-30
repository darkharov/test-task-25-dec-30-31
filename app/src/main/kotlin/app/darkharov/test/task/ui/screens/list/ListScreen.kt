package app.darkharov.test.task.ui.screens.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import app.darkharov.test.task.ui.commons.AppScreenPreview
import app.darkharov.test.task.ui.commons.theme.AppTheme

@Composable
fun ListScreen() {
    Text("List")
}

@Composable
@AppScreenPreview
private fun ListScreenPreview() {
    AppTheme {
        ListScreen()
    }
}
