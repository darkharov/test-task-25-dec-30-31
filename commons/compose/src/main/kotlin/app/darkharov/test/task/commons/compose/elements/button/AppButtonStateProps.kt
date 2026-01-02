package app.darkharov.test.task.commons.compose.elements.button

import androidx.compose.runtime.Immutable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Immutable
enum class AppButtonStateProps {
    Normal,
    Progress,
    Disabled,
    ;
    companion object {
        fun enabledIf(enabled: Boolean) =
            if (enabled) {
                Normal
            } else {
                Disabled
            }
    }
}

internal class AppButtonStateMocks : PreviewParameterProvider<AppButtonStateProps> {
    override val values = AppButtonStateProps.entries.asSequence()
}
