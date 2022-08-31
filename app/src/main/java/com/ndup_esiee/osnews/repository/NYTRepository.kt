package com.ndup_esiee.osnews.repository

import com.ndup_esiee.osnews.repository.model.Sections

class NYTRepository: INYTRepository {

    private val service by lazy { NYTServiceFactory.service }

    override suspend fun getSections(): Sections = service.fetchSections().sections
}