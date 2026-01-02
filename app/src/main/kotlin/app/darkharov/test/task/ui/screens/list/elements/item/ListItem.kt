package app.darkharov.test.task.ui.screens.list.elements.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import app.darkharov.test.task.ui.commons.theme.AppTheme

@Composable
internal fun ListItem(
    props: ListItemProps,
    callbacks: ListItemCallbacks,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .clickable {
                callbacks.onClick(
                    itemId = props.id,
                )
            },
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = props.title,
                modifier = Modifier
                    .weight(1f),
            )
            Checkbox(
                checked = props.checked,
                onCheckedChange = { newValue ->
                    callbacks.onCheckedChange(
                        itemId = props.id,
                        newValue = newValue,
                    )
                },
            )
        }
    }
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
