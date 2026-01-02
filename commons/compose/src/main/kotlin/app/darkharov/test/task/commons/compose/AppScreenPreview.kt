package app.darkharov.test.task.commons.compose

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Preview(   // tablet in dark mode
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@PreviewLightDark
annotation class AppScreenPreview
