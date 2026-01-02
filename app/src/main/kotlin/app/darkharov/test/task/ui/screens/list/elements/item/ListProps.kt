package app.darkharov.test.task.ui.screens.list.elements.item

import androidx.compose.runtime.Immutable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
internal sealed class ListProps {

    @Immutable
    object Loading : ListProps()

    @Immutable
    object Empty : ListProps()

    @Immutable
    data class Items(
        val items: ImmutableList<ListItemProps>,
    ) : ListProps()
}

internal class ListMocks : PreviewParameterProvider<ListProps> {

    override val values = sequenceOf(
        ListProps.Loading,
        ListProps.Empty,
        ListProps.Items(
            items = (1..10)
                .map { id ->
                    ListItemProps(
                        id = id,
                        title = "Item $id",
                        checked = (id / 2) % 2 == 0,
                    )
                }
                .toImmutableList(),
        ),
    )
}
