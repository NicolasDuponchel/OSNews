package com.ndup_esiee.osnews.objectgraph

import com.ndup_esiee.osnews.repository.INYTRepository
import com.ndup_esiee.osnews.repository.NYTRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<INYTRepository> { NYTRepository() }
}