package app.darkharov.test.task.ui.screens.log.in_

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import app.darkharov.test.task.R
import app.darkharov.test.task.ui.commons.AppScreenPreview
import app.darkharov.test.task.ui.commons.EventConsumer
import app.darkharov.test.task.ui.commons.customOnFocusCallbacks
import app.darkharov.test.task.ui.commons.elements.button.AppButton
import app.darkharov.test.task.ui.commons.elements.fields.AppSecureTextField
import app.darkharov.test.task.ui.commons.elements.fields.AppTextField
import app.darkharov.test.task.ui.commons.theme.AppTheme
import kotlinx.coroutines.channels.ReceiveChannel

@Composable
fun LogInScreen(
    delegate: LogInDelegate,
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<LogInViewModel>()
    val events = viewModel.events()
    EventConsumer(
        events = events,
        delegate = delegate,
    )
    LogInScreen(
        props = viewModel,
        callbacks = viewModel,
        modifier = modifier,
    )
}

@Composable
private fun EventConsumer(
    events: ReceiveChannel<LogInViewModel.Event>,
    delegate: LogInDelegate,
) {
    EventConsumer(
        events = events,
    ) { event ->
        when (event) {
            is LogInViewModel.Event.OnLoggedIn -> {
                delegate.goToListScreen()
            }
            is LogInViewModel.Event.OnLoginFailed -> {
                delegate.showSnackbar(message = event.message)
            }
        }
    }
}

@Composable
internal fun LogInScreen(
    props: LogInProps,
    callbacks: LogInCallbacks,
    modifier: Modifier = Modifier,
) {
    val passwordFocusRequester = remember { FocusRequester() }
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .widthIn(max = 520.dp)
                .fillMaxSize()
                .padding(horizontal = dimensionResource(R.dimen.margin_16_32_64)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = 12.dp),
            )
            AppTextField(
                state = props.login,
                enabled = props.fieldsEnabled,
                label = stringResource(R.string.name),
                supportingText = stringResource(R.string.name_requirements),
                isError = props.loginError,
                imeAction = ImeAction.Next,
                onKeyboardAction = {
                    passwordFocusRequester.requestFocus()
                },
                modifier = Modifier
                    .customOnFocusCallbacks(
                        onUnfocused = {
                            callbacks.onLoginUnfocused()
                        },
                    ),
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp),
            )
            AppSecureTextField(
                state = props.password,
                label = stringResource(R.string.password),
                supportingText = stringResource(R.string.password_requirements),
                isError = props.passwordError,
                imeAction = ImeAction.Done,
                onKeyboardAction = {
                    callbacks.onAttemptToComplete()
                },
                modifier = Modifier
                    .customOnFocusCallbacks(
                        onUnfocused = {
                            callbacks.onPasswordUnfocused()
                        },
                    )
                    .focusRequester(passwordFocusRequester),
                enabled = props.fieldsEnabled,
            )
            AppButton(
                text = stringResource(R.string.log_in),
                state = props.logInButtonState,
                onClick = {
                    callbacks.onAttemptToComplete()
                },
                modifier = Modifier
                    .padding(
                        vertical = 12.dp,
                    ),
            )
            Spacer(
                modifier = Modifier
                    .windowInsetsBottomHeight(WindowInsets.navigationBars),
            )
        }
    }
}

@Composable
@AppScreenPreview
private fun LogInScreenPreview(
    @PreviewParameter(
        provider = LogInMocks::class,
    )
    props: LogInProps,
) {
    AppTheme {
        Scaffold { innerPadding ->
            LogInScreen(
                props = props,
                callbacks = LogInCallbacksMock,
                modifier = Modifier
                    .padding(innerPadding),
            )
        }
    }
}
