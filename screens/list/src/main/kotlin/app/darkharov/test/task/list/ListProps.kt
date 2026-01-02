package app.darkharov.test.task.list

import androidx.compose.runtime.Immutable
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.darkharov.test.task.list.elements.item.ListItemProps
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
internal sealed class ListProps {

    @Immutable
    data object Loading : ListProps()

    @Immutable
    data object Empty : ListProps()

    @Immutable
    data class Items(
        val selectAllState: ToggleableState,
        val items: ImmutableList<ListItemProps>,
    ) : ListProps()
}

internal class ListMocks : PreviewParameterProvider<ListProps> {

    override val values = sequenceOf(
        ListProps.Loading,
        ListProps.Empty,
        ListProps.Items(
            selectAllState = ToggleableState.Indeterminate,
            items = List(10) { index ->
                ListItemProps(
                    id = index,
                    title = "Item $index",
                    checked = (index / 2) % 2 == 0,
                )
            }.toImmutableList(),
        ),
    )
}
