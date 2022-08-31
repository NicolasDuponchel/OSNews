package com.ndup_esiee.osnews.repository

import com.ndup_esiee.osnews.repository.model.NYTMainResponse
import com.ndup_esiee.osnews.repository.model.NewsWires
import com.ndup_esiee.osnews.repository.model.Sections
import retrofit2.http.GET
import retrofit2.http.Path

interface NYTApiServices {
    @GET("section-list.json")
    suspend fun fetchSections(): NYTMainResponse<Sections>

    @GET("all/all.json")
    suspend fun fetchNews(): NYTMainResponse<NewsWires>

    @GET("all/{section}.json")
    suspend fun fetchNews(@Path("section") sectionName: String): NYTMainResponse<NewsWires>
}