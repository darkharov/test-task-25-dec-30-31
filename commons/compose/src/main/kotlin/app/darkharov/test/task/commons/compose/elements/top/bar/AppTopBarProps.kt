package app.darkharov.test.task.commons.compose.elements.top.bar

import androidx.compose.runtime.Immutable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Immutable
data class AppTopBarProps(
    val title: String,
    val upIconVisible: Boolean,
    val logOutIconVisible: Boolean,
)

internal class AppTopBarMocks : PreviewParameterProvider<AppTopBarProps> {

    override val values = sequenceOf(
        AppTopBarProps(
            title = "Title",
            upIconVisible = true,
            logOutIconVisible = true,
        ),
        AppTopBarProps(
            title = "Title",
            upIconVisible = false,
            logOutIconVisible = false,
        ),
    )
}