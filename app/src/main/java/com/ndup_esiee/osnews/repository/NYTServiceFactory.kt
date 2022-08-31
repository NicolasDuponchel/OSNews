package com.ndup_esiee.osnews.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ndup_esiee.osnews.BuildConfig
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
object NYTServiceFactory {

    private const val TimesNewsWireBaseUrl = "https://api.nytimes.com/svc/news/v3/content/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(TimesNewsWireBaseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(client)
        .build()

    private val client get() = OkHttpClient.Builder()
        .addInterceptor { apiKeyAsQuery(it) }
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
        chain.request()
            .newBuilder()
            .url(chain.request().url.newBuilder().addQueryParameter("api-key", BuildConfig.apiKey).build())
            .build()
    )

    val service: NYTServices = retrofit.create(NYTServices::class.java)

}