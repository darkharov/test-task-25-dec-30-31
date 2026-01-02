package app.darkharov.test.task.list.elements.check.all

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import app.darkharov.test.task.commons.compose.theme.AppTheme
import app.darkharov.test.task.list.R
import app.darkharov.test.task.list.elements.base.item.BaseItem

@Composable
internal fun CheckAll(
    state: ToggleableState,
    callbacks: CheckAllCallbacks,
    modifier: Modifier = Modifier,
    windowInsetsPadding: WindowInsets = WindowInsets(0.dp),
) {
    BaseItem(
        title = stringResource(R.string.check_all),
        state = state,
        modifier = modifier
            .triStateToggleable(
                state = state,
                onClick = {
                    callbacks.onCheckAllClick(
                        newValue = when (state) {
                            ToggleableState.Off,
                            ToggleableState.Indeterminate,
                                -> {
                                true
                            }
                            ToggleableState.On -> {
                                false
                            }
                        }
                    )
                },
            ),
        color = MaterialTheme.colorScheme.inversePrimary,
        windowInsetsPadding = windowInsetsPadding,
        titleStyle = LocalTextStyle.current
            .copy(
                fontWeight = FontWeight.Bold,
            )
    )
}

@PreviewLightDark
@Composable
private fun CheckAllPreview() {
    AppTheme {
        CheckAll(
            state = ToggleableState.Indeterminate,
            callbacks = CheckAllCallbacksMock,
        )
    }
}
