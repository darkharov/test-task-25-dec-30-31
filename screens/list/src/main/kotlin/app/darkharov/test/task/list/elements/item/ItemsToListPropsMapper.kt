package app.darkharov.test.task.list.elements.item

import androidx.compose.ui.state.ToggleableState
import app.darkharov.test.task.domain.Item
import app.darkharov.test.task.list.ListProps
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

internal object ItemsToListPropsMapper {

    fun transform(items: List<Item>): ListProps =
        if (items.isEmpty()) {
            ListProps.Empty
        } else {
            val checkedCount = items.count { it.checked }
            val uncheckedCount = items.count { !(it.checked) }
            ListProps.Items(
                selectAllState = when {
                    checkedCount == items.size -> ToggleableState.On
                    uncheckedCount == items.size -> ToggleableState.Off
                    else -> ToggleableState.Indeterminate
                },
                items = transformItemsList(items)
            )
        }

    private fun transformItemsList(items: List<Item>): ImmutableList<ListItemProps> =
        items
            .map { transformItem(it) }
            .toImmutableList()

    private fun transformItem(item: Item): ListItemProps =
        ListItemProps(
            id = item.id,
            title = item.title,
            checked = item.checked,
        )
}
