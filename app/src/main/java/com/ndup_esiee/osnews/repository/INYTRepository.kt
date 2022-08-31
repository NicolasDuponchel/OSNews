package com.ndup_esiee.osnews.repository

import com.ndup_esiee.osnews.repository.model.Sections

interface INYTRepository {

    suspend fun getSections(): Sections

}