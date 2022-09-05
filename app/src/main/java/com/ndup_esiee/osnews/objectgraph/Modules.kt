package com.ndup_esiee.osnews.objectgraph

import com.ndup_esiee.osnews.presentation.IMainListener
import com.ndup_esiee.osnews.presentation.MainModel
import com.ndup_esiee.osnews.presentation.MainViewModel
import com.ndup_esiee.osnews.repository.INYTRepository
import com.ndup_esiee.osnews.repository.NYTApiServices
import com.ndup_esiee.osnews.repository.NYTRepository
import com.ndup_esiee.osnews.repository.NYTServiceFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single<INYTRepository> { NYTRepository() }
    single<NYTApiServices> { NYTServiceFactory.service }
}

val presentationModule = module {
    single<MainModel> { MainModel(sections = emptyList(), newsWires = emptyList()) }
    viewModel { MainViewModel(get(), get(), it[0]) } bind IMainListener::class
}