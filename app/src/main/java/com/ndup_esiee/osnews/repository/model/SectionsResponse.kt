package com.ndup_esiee.osnews.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SectionsResponse(
    @SerialName("status") var status: String,
    @SerialName("copyright") var copyright: String,
    @SerialName("num_results") var numResults: Int,
    @SerialName("results") var sections: Sections,
)

@Serializable
data class Section(
    @SerialName("section") val section: String,
    @SerialName("display_name") val displayName: String,
)

typealias Sections = List<Section>
