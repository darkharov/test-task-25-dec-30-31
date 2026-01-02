package app.darkharov.test.task.temp

import app.darkharov.test.task.domain.ItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ItemRepositoryModule {

    @Binds
    fun itemRepository(impl: ItemRepositoryMock): ItemRepository
}
