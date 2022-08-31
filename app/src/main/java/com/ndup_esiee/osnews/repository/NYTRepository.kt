package com.ndup_esiee.osnews.repository

import com.ndup_esiee.osnews.repository.model.Sections

class NYTRepository {

    private val service by lazy { NYTServiceFactory.service }

    suspend fun getSections(): Sections = service.fetchSections().sections
}