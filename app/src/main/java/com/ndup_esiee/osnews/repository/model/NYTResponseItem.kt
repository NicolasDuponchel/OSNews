package com.ndup_esiee.osnews.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
sealed class NYTResponseItem
typealias NYTResponseItems = List<NYTResponseItem>


@Serializable
data class Section(
    @SerialName("section") val name: String,
    @SerialName("display_name") val displayName: String,
) : NYTResponseItem()
typealias Sections = List<Section>


//TODO NDU: manage all those ugly nullable cases
@Serializable
data class NewsWire(
    @SerialName("slug_name") var slugName: String? = null,
    @SerialName("section") var section: String? = null,
    @SerialName("subsection") var subsection: String? = null,
    @SerialName("title") var title: String? = null,
    @SerialName("abstract") var abstract: String? = null,
    @SerialName("uri") var uri: String? = null,
    @SerialName("url") var url: String? = null,
    @SerialName("byline") var byline: String? = null,
    @SerialName("thumbnail_standard") var thumbnailStandard: String? = null,
    @SerialName("item_type") var itemType: String? = null,
    @SerialName("source") var source: String? = null,
    @SerialName("updated_date") var updatedDate: String? = null,
    @SerialName("created_date") var createdDate: String? = null,
    @SerialName("published_date") var publishedDate: String? = null,
    @SerialName("first_published_date") var firstPublishedDate: String? = null,
    @SerialName("material_type_facet") var materialTypeFacet: String? = null,
    @SerialName("kicker") var kicker: String? = null,
    @SerialName("subheadline") var subHeadline: String? = null,
    @SerialName("des_facet") var desFacet: List<String>? = emptyList(),
    @SerialName("org_facet") var orgFacet: List<String>? = null,
    @SerialName("per_facet") var perFacet: List<String>? = null,
    @SerialName("geo_facet") var geoFacet: List<String>? = null,
    @SerialName("related_urls") var relatedUrls: List<RelatedUrls>? = emptyList(),
    @SerialName("multimedia") var multimedia: List<Multimedia>? = emptyList(),
) : NYTResponseItem()
typealias NewsWires = List<NewsWire>

@Serializable
data class RelatedUrls(
    @SerialName("suggested_link_text") val suggestedLinkText: String? = null,
    @SerialName("url") val url: String? = null,
)

@Serializable
data class Multimedia(
    @SerialName("url") var url: String? = null,
    @SerialName("format") var format: String? = null,
    @SerialName("height") var height: Int? = null,
    @SerialName("width") var width: Int? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("subtype") var subtype: String? = null,
    @SerialName("caption") var caption: String? = null,
    @SerialName("copyright") var copyright: String? = null,
)