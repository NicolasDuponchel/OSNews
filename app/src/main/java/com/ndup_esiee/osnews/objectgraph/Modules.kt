package com.ndup_esiee.osnews.objectgraph

import com.ndup_esiee.osnews.presentation.MainModel
import com.ndup_esiee.osnews.repository.INYTRepository
import com.ndup_esiee.osnews.repository.NYTApiServices
import com.ndup_esiee.osnews.repository.NYTRepository
import com.ndup_esiee.osnews.repository.NYTServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideMainModel() = MainModel(emptyList(), emptyList())

}


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    companion object {
        @Singleton
        @Provides
        fun provideNYTApiServices(): NYTApiServices = NYTServiceFactory.service
    }

    @Binds
    abstract fun provideINYTRepository(repo: NYTRepository): INYTRepository

}
