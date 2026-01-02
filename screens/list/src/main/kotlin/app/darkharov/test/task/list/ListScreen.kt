package app.darkharov.test.task.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.darkharov.test.task.commons.compose.AppScreenPreview
import app.darkharov.test.task.commons.compose.EventConsumer
import app.darkharov.test.task.commons.compose.elements.AppNoContentMessage
import app.darkharov.test.task.commons.compose.elements.AppPreloader
import app.darkharov.test.task.commons.compose.theme.AppTheme
import app.darkharov.test.task.list.elements.check.all.CheckAll
import app.darkharov.test.task.list.elements.item.ListCallbacks
import app.darkharov.test.task.list.elements.item.ListCallbacksMock
import app.darkharov.test.task.list.elements.item.ListItem

@Composable
fun ListScreen(
    delegate: ListDelegate,
) {
    val viewModel = hiltViewModel<ListViewModel>()
    val props by viewModel.props.collectAsStateWithLifecycle()
    EventConsumer(
        events = viewModel.events(),
    ) { event ->
        when (event) {
            is ListViewModel.Event.OnClicked -> {
                delegate.goToItemDetails(
                    itemId = event.itemId,
                )
            }
        }
    }
    ListScreen(
        props = props,
        callbacks = viewModel,
    )
}

@Composable
private fun ListScreen(
    props: ListProps,
    callbacks: ListCallbacks,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
    ) {
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
                    props = props,
                    callbacks = callbacks,
                )
            }
        }
    }
}

@Composable
private fun Items(
    props: ListProps.Items,
    callbacks: ListCallbacks,
) {
    Column {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
        ) {
            items(
                items = props.items,
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
        CheckAll(
            state = props.selectAllState,
            callbacks = callbacks,
            windowInsetsPadding = WindowInsets.navigationBars,
        )
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
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}
