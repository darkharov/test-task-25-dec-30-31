package app.darkharov.test.task.list.elements.base.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.darkharov.test.task.commons.compose.theme.AppTheme
import app.darkharov.test.task.list.R

@Composable
fun BaseItem(
    title: String,
    onCheckBoxClick: (() -> Unit)? = null,
    state: ToggleableState,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    windowInsetsPadding: WindowInsets = WindowInsets(0.dp),
    titleStyle: TextStyle = LocalTextStyle.current,
) {
    Surface(
        modifier = modifier,
        color = color,
    ) {
        Row(
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = dimensionResource(R.dimen.margin_16_32_64),
                )
                .windowInsetsPadding(windowInsetsPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .weight(1f),
                style = titleStyle,
            )
            CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 12.dp) {
                TriStateCheckbox(
                    state = state,
                    onClick = onCheckBoxClick,
                    modifier = modifier,
                )
            }
        }
    }
}

@Preview
@Composable
private fun BaseItemPreview() {
    AppTheme {
        BaseItem(
            title = "Title",
            state = ToggleableState.On,
        )
    }
}
