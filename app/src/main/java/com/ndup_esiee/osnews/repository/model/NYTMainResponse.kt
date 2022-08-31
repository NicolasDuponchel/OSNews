package com.ndup_esiee.osnews.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NYTMainResponse<T : NYTResponseItems>(
    @SerialName("status") var status: String,
    @SerialName("copyright") var copyright: String,
    @SerialName("num_results") var numResults: Int,
    @SerialName("results") var results: T,
)

