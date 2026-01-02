package app.darkharov.test.task.list.elements.item

import androidx.compose.runtime.Immutable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Immutable
internal data class ListItemProps(
    val id: Int,
    val title: String,
    val checked: Boolean,
)

internal class ListItemMocks : PreviewParameterProvider<ListItemProps> {

    private val prototype =
        ListItemProps(
            id = 1,
            title = "Title",
            checked = false,
        )

    override val values = sequenceOf(
        prototype.copy(checked = false),
        prototype.copy(checked = true),
    )
}
