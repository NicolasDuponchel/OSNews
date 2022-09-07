package com.ndup_esiee.osnews.repository

import com.ndup_esiee.osnews.repository.model.NewsWires
import com.ndup_esiee.osnews.repository.model.Section
import com.ndup_esiee.osnews.repository.model.Sections
import javax.inject.Inject

class NYTRepository @Inject constructor(
    private val service: NYTApiServices,
) : INYTRepository {

    override suspend fun getSections(): Sections = service.fetchSections().results

    override suspend fun getNews(section: Section?): NewsWires = (
            section
                ?.let { service.fetchNews(it.name) }
                ?: service.fetchNews()
            ).results

}