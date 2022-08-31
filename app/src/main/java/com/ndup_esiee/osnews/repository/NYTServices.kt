package com.ndup_esiee.osnews.repository

import com.ndup_esiee.osnews.repository.model.SectionsResponse
import retrofit2.http.GET

interface NYTServices {
    @GET("section-list.json")
    suspend fun fetchSections(): SectionsResponse

    @GET("TODO") //TODO NDU
    fun getNews(section: String): List<String> //TODO NDU
}