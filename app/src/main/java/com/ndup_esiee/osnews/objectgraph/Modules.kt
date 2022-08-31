package com.ndup_esiee.osnews.objectgraph

import com.ndup_esiee.osnews.repository.INYTRepository
import com.ndup_esiee.osnews.repository.NYTApiServices
import com.ndup_esiee.osnews.repository.NYTRepository
import com.ndup_esiee.osnews.repository.NYTServiceFactory
import org.koin.dsl.module

val repositoryModule = module {
    single<INYTRepository> { NYTRepository() }
    single<NYTApiServices> { NYTServiceFactory.service }
}