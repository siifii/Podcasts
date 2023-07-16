package com.siifii.thamanyah.core.di.playlist

import com.siifii.thamanyah.core.mapper.playlist.PlaylistResponseMapper
import com.siifii.thamanyah.core.mapper.playlist.PlaylistResponseMapperImpl
import com.siifii.thamanyah.data.datasource.rest.PublicApiFunctions
import com.siifii.thamanyah.data.datasource.rest.resources.playlist.PlaylistDataSource
import com.siifii.thamanyah.data.datasource.rest.resources.playlist.PlaylistDataSourceImpl
import com.siifii.thamanyah.data.repository.playlist.PlaylistRepositoryImp
import com.siifii.thamanyah.domain.playlist.repository.PlaylistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PlaylistModule {

    @Provides
    fun providePlaylistDataSource(api: PublicApiFunctions): PlaylistDataSource =
        PlaylistDataSourceImpl(api)

    @Provides
    fun PlaylistResponseMapper(): PlaylistResponseMapper = PlaylistResponseMapperImpl()

    @Provides
    fun provideRegistrationRepository(
        dataSource: PlaylistDataSource,
        PlaylistResponseMapper: PlaylistResponseMapper,
    ): PlaylistRepository =
        PlaylistRepositoryImp(
            dataSource,
            PlaylistResponseMapper,
        )
}
