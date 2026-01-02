package app.darkharov.test.task.ui

import androidx.navigation3.runtime.NavKey
import app.darkharov.test.task.R
import kotlinx.serialization.Serializable

@Serializable
sealed class AppScreenKey(
    val toolbarTitleId: Int,
    val requiresLogin: Boolean = true,
) : NavKey {

    @Serializable
    data object Login : AppScreenKey(
        toolbarTitleId = R.string.log_in,
        requiresLogin = false,
    )

    @Serializable
    data object List : AppScreenKey(
        toolbarTitleId = R.string.list,
    )

    @Serializable
    data class Details(
        val itemId: Int?,
    ) : AppScreenKey(
        toolbarTitleId = R.string.details,
    )

    companion object {
        val Home: AppScreenKey = List // alias
    }
}