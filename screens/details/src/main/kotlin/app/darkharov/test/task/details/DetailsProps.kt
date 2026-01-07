package app.darkharov.test.task.details

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Stable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.darkharov.test.task.commons.compose.elements.button.AppButtonStateProps

@Stable
interface DetailsProps {
    val title: TextFieldState
    val titleFieldEnabled: Boolean
    val checked: Boolean
    val saveButtonState: AppButtonStateProps
}

class DetailsMock : PreviewParameterProvider<DetailsProps> {

    override val values = sequenceOf<DetailsProps>(
        Prototype(),
        Prototype(
            title = TextFieldState(
                initialText = "Title",
            ),
            titleFieldEnabled = true,
            checked = true,
            saveButtonState = AppButtonStateProps.Normal,
        ),
    )

    private data class Prototype(
        override val title: TextFieldState = TextFieldState(),
        override var titleFieldEnabled: Boolean = false,
        override var checked: Boolean = false,
        override val saveButtonState: AppButtonStateProps = AppButtonStateProps.Disabled,
    ) : DetailsProps
}
