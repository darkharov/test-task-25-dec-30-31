package app.darkharov.test.task.list

import androidx.lifecycle.ViewModel
import app.darkharov.test.task.list.item.ListCallbacks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ListViewModel @Inject constructor() :
    ViewModel(),
    ListCallbacks {

    override fun onClick(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onCheckedChange(itemId: Int, newValue: Boolean) {
        TODO("Not yet implemented")
    }
}
