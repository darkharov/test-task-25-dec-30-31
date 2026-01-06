package app.darkharov.test.task.data.internal.di

import app.darkharov.test.task.data.ItemRepositoryImpl
import app.darkharov.test.task.domain.ItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ImplsModule {

    @Binds
    fun itemRepository(impl: ItemRepositoryImpl): ItemRepository
}
