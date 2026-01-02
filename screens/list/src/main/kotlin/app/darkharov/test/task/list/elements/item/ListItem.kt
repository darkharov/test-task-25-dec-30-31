package app.darkharov.test.task.list.elements.item

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import app.darkharov.test.task.commons.compose.theme.AppTheme
import app.darkharov.test.task.list.elements.base.item.BaseItem

@Composable
internal fun ListItem(
    props: ListItemProps,
    callbacks: ListItemCallbacks,
    modifier: Modifier = Modifier,
) {
    BaseItem(
        title = props.title,
        state = ToggleableState(props.checked),
        onCheckBoxClick = {
            callbacks.onCheckedChange(
                itemId = props.id,
                newValue = !(props.checked),
            )
        },
        modifier = modifier
            .clickable {
                callbacks.onClick(
                    itemId = props.id,
                )
            },
    )
}

@Composable
@PreviewLightDark
private fun ListItemPreview(
    @PreviewParameter(
        provider = ListItemMocks::class,
    )
    props: ListItemProps,
) {
    AppTheme {
        ListItem(
            props = props,
            callbacks = ListItemCallbacksMock,
            modifier = Modifier,
        )
    }
}
