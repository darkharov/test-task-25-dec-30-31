package app.darkharov.test.task.ui.screens.list.elements.item

import androidx.lifecycle.ViewModel
import app.darkharov.test.task.ui.screens.list.ListCallbacks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor() :
    ViewModel(),
    ListCallbacks {

    override fun onClick(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onCheckedChange(itemId: Int, newValue: Boolean) {
        TODO("Not yet implemented")
    }
}
