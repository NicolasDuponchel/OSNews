package com.ndup_esiee.osnews.repository

import com.ndup_esiee.osnews.repository.model.NewsWires
import com.ndup_esiee.osnews.repository.model.Section
import com.ndup_esiee.osnews.repository.model.Sections

/**
 * Currently using API model. Could be great to have a presentation model mapping it.
 * I'll see when I'll split the app into modules, but for now I'm fine with it.
 * Probably NewsWires is overkill, I'll need to have a simplified model.
 */
interface INYTRepository {

    suspend fun getSections(): Sections

    suspend fun getNews(section: Section? = null): NewsWires

}