package app.darkharov.test.task.domain

import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun all(): Flow<List<Item>>
    suspend fun setChecked(id: Int, checked: Boolean)
    suspend fun setAllChecked(checked: Boolean)
}
