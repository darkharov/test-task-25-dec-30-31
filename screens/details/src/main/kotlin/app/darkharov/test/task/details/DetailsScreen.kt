package app.darkharov.test.task.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import app.darkharov.test.task.commons.compose.AppScreenPreview
import app.darkharov.test.task.commons.compose.EventConsumer
import app.darkharov.test.task.commons.compose.elements.button.AppButton
import app.darkharov.test.task.commons.compose.elements.fields.AppTextField
import app.darkharov.test.task.commons.compose.theme.AppTheme

const val MAX_LENGTH_OF_TEXT_FIELD = 100

@Composable
fun DetailsScreen(
    itemId: Int?,
    delegate: DetailDelegate,
) {
    val viewModel = hiltViewModel<DetailsViewModel, DetailsViewModel.Factory>(
        creationCallback = { factory ->
            factory.create(itemId = itemId)
        }
    )
    EventConsumer(viewModel.events()) { event ->
        when (event) {
            DetailsViewModel.Event.OnNewValueSaved -> {
                delegate.exit()
            }
        }
    }
    DetailsScreen(
        props = viewModel,
        callbacks = viewModel,
    )
}

@Composable
private fun DetailsScreen(
    props: DetailsProps,
    callbacks: DetailsCallbacks,
) {
    Surface {
        Box(
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(R.dimen.margin_16_32_64),
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 520.dp)
                    .fillMaxSize()
                    .padding(
                        horizontal = dimensionResource(R.dimen.margin_16_32_64),
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                AppTextField(
                    state = props.title,
                    label = stringResource(R.string.item_text),
                    supportingText = "${props.title.text.length} / $MAX_LENGTH_OF_TEXT_FIELD",
                    imeAction = ImeAction.Done,
                    onKeyboardAction = {
                        callbacks.onSave()
                    },
                    inputTransformation = InputTransformation.maxLength(MAX_LENGTH_OF_TEXT_FIELD),
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = !(props.saving),
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp),
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .clickable {
                            callbacks.onCheckedChange(
                                newValue = !(props.checked),
                            )
                        }
                        .padding(8.dp),
                ) {
                    Text(
                        text = stringResource(R.string.checked),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .weight(1f),
                    )
                    Checkbox(
                        checked = props.checked,
                        onCheckedChange = null,
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(8.dp),
                )
                AppButton(
                    text = "Save",
                    state = props.saveButtonState,
                    onClick = {
                        callbacks.onSave()
                    },
                )
            }
        }
    }
}

@AppScreenPreview
@Composable
private fun DetailsScreenPreview(
    @PreviewParameter(
        provider = DetailsMock::class,
    )
    props: DetailsProps,
) {
    AppTheme {
        DetailsScreen(
            props = props,
            callbacks = DetailsCallbacksMock,
        )
    }
}
