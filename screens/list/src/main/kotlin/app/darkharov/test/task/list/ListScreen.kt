package app.darkharov.test.task.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import app.darkharov.test.task.commons.compose.AppScreenPreview
import app.darkharov.test.task.commons.compose.elements.AppNoContentMessage
import app.darkharov.test.task.commons.compose.elements.AppPreloader
import app.darkharov.test.task.commons.compose.theme.AppTheme
import app.darkharov.test.task.list.item.ListCallbacks
import app.darkharov.test.task.list.item.ListCallbacksMock
import app.darkharov.test.task.list.item.ListItem
import app.darkharov.test.task.list.item.ListItemCallbacks
import app.darkharov.test.task.list.item.ListItemProps
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ListScreen() {
    ListScreen(
        props = ListProps.Items(
            persistentListOf(),
        ),
        callbacks = ListCallbacksMock,
    )
}

@Composable
internal fun ListScreen(
    props: ListProps,
    callbacks: ListCallbacks,
) {
    Surface {
        when (props) {
            is ListProps.Loading -> {
                AppPreloader()
            }
            is ListProps.Empty -> {
                AppNoContentMessage(
                    text = stringResource(R.string.list_is_empty),
                )
            }
            is ListProps.Items -> {
                Items(
                    items = props.items,
                    callbacks = callbacks,
                )
            }
        }
    }
}

@Composable
private fun Items(
    items: ImmutableList<ListItemProps>,
    callbacks: ListItemCallbacks,
) {
    LazyColumn {
        items(
            items = items,
            key = {
                it.id
            },
            contentType = {
                "List Item"
            },
        ) {
            ListItem(
                props = it,
                callbacks = callbacks,
            )
        }
    }
}

@Composable
@AppScreenPreview
private fun ListScreenPreview(
    @PreviewParameter(
        provider = ListMocks::class,
    )
    props: ListProps,
) {
    AppTheme {
        ListScreen(
            props = props,
            callbacks = ListCallbacksMock,
        )
    }
}
