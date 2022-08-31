package com.ndup_esiee.osnews.repository

import com.ndup_esiee.osnews.repository.model.NewsWires
import com.ndup_esiee.osnews.repository.model.Section
import com.ndup_esiee.osnews.repository.model.Sections
import org.koin.java.KoinJavaComponent.inject

class NYTRepository : INYTRepository {

    private val service: NYTApiServices by inject(NYTApiServices::class.java)

    override suspend fun getSections(): Sections = service.fetchSections().results

    override suspend fun getNews(section: Section?): NewsWires = (
            section
                ?.let { service.fetchNews(it.name) }
                ?: service.fetchNews()
            ).results

}