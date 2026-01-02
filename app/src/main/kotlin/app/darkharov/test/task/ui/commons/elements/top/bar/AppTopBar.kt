package app.darkharov.test.task.ui.commons.elements.top.bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import app.darkharov.test.task.R
import app.darkharov.test.task.ui.commons.theme.AppIcons
import app.darkharov.test.task.ui.commons.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppTopBar(
    props: AppTopBarProps,
    callbacks: AppTopBarCallbacks,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = props.title,
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (props.upIconVisible) {
                Icon(
                    painter = rememberVectorPainter(AppIcons.arrowBack),
                    contentDescription = stringResource(R.string.go_back),
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            callbacks.onUpClick()
                        }
                        .padding(12.dp),
                )
            }
        },
        actions = {
            if (props.logOutIconVisible) {
                Icon(
                    painter = rememberVectorPainter(AppIcons.logout),
                    contentDescription = stringResource(R.string.log_out),
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { callbacks.onLogOutClick() }
                        .padding(12.dp),
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
    )
}

@PreviewLightDark
@Composable
private fun AppTopBarPreview(
    @PreviewParameter(
        provider = AppTopBarMocks::class,
    )
    props: AppTopBarProps,
) {
    AppTheme {
        AppTopBar(
            props = props,
            callbacks = AppTopBarCallbacksMock,
        )
    }
}
