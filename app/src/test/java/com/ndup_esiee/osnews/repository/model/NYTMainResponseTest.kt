package com.ndup_esiee.osnews.repository.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class NYTMainResponseTest {

    @Test
    fun `repository response serialization`() {
        val json =
            """{"status":"OK","copyright":"Copyright (c) 2022 The New York Times Company.  All Rights Reserved.","num_results":50,"results":[{"section":"admin","display_name":"Admin"},{"section":"arts","display_name":"Arts"},{"section":"automobiles","display_name":"Automobiles"},{"section":"books","display_name":"Books"},{"section":"briefing","display_name":"Briefing"},{"section":"business","display_name":"Business"},{"section":"climate","display_name":"Climate"},{"section":"corrections","display_name":"Corrections"},{"section":"crosswords \u0026 games","display_name":"Crosswords \u0026 Games"},{"section":"education","display_name":"Education"},{"section":"en español","display_name":"En Español"},{"section":"fashion","display_name":"Fashion"},{"section":"food","display_name":"Food"},{"section":"guides","display_name":"Guides"},{"section":"health","display_name":"Health"},{"section":"home \u0026 garden","display_name":"Home \u0026 Garden"},{"section":"home page","display_name":"Home Page"},{"section":"job market","display_name":"Job Market"},{"section":"lens","display_name":"Lens"},{"section":"magazine","display_name":"Magazine"},{"section":"movies","display_name":"Movies"},{"section":"multimedia/photos","display_name":"Multimedia/Photos"},{"section":"new york","display_name":"New York"},{"section":"obituaries","display_name":"Obituaries"},{"section":"opinion","display_name":"Opinion"},{"section":"parenting","display_name":"Parenting"},{"section":"podcasts","display_name":"Podcasts"},{"section":"reader center","display_name":"Reader Center"},{"section":"real estate","display_name":"Real Estate"},{"section":"science","display_name":"Science"},{"section":"smarter living","display_name":"Smarter Living"},{"section":"sports","display_name":"Sports"},{"section":"style","display_name":"Style"},{"section":"sunday review","display_name":"Sunday Review"},{"section":"t brand","display_name":"T Brand"},{"section":"t magazine","display_name":"T Magazine"},{"section":"technology","display_name":"Technology"},{"section":"the learning network","display_name":"The Learning Network"},{"section":"the upshot","display_name":"The Upshot"},{"section":"the weekly","display_name":"The Weekly"},{"section":"theater","display_name":"Theater"},{"section":"times insider","display_name":"Times Insider"},{"section":"today’s paper","display_name":"Today’s Paper"},{"section":"travel","display_name":"Travel"},{"section":"u.s.","display_name":"U.S."},{"section":"universal","display_name":"Universal"},{"section":"video","display_name":"Video"},{"section":"well","display_name":"Well"},{"section":"world","display_name":"World"},{"section":"your money","display_name":"Your Money"}]}"""
        val sections = listOf(
            "Admin",
            "Arts",
            "Automobiles",
            "Books",
            "Briefing",
            "Business",
            "Climate",
            "Corrections",
            "Crosswords & Games",
            "Education",
            "En Español",
            "Fashion",
            "Food",
            "Guides",
            "Health",
            "Home & Garden",
            "Home Page",
            "Job Market",
            "Lens",
            "Magazine",
            "Movies",
            "Multimedia/Photos",
            "New York",
            "Obituaries",
            "Opinion",
            "Parenting",
            "Podcasts",
            "Reader Center",
            "Real Estate",
            "Science",
            "Smarter Living",
            "Sports",
            "Style",
            "Sunday Review",
            "T Brand",
            "T Magazine",
            "Technology",
            "The Learning Network",
            "The Upshot",
            "The Weekly",
            "Theater",
            "Times Insider",
            "Today’s Paper",
            "Travel",
            "U.S.",
            "Universal",
            "Video",
            "Well",
            "World",
            "Your Money",
        )

        assertEquals(sections, Json.decodeFromString<NYTMainResponse<Sections>>(json).results.map { it.displayName })
        assertEquals("OK", Json.decodeFromString<NYTMainResponse<Sections>>(json).status)
        assertEquals(50, Json.decodeFromString<NYTMainResponse<Sections>>(json).numResults)
    }


    @Test
    fun `news wire response serialization`() {
        val newsWireJson = """{"slug_name":"31travel-pacific-fader","section":"Travel","subsection":"","title":"31 travel pacific fader","abstract":"31 travel pacific fader","uri":"nyt://slideshow/974a43fd-0ae6-572b-998b-a9862cafb124","url":"https://static01.nyt.com/slideshow/2022/08/31/travel/31-travel-pacific-fader.html","byline":"","thumbnail_standard":"https://static01.nyt.com/images/2022/08/31/travel/31travel-pacific-fader-slide-7WCP/31travel-pacific-fader-slide-7WCP-thumbStandard.jpg","item_type":"Slideshow","source":"New York Times","updated_date":"2022-08-31T08:46:46-04:00","created_date":"2022-08-31T08:46:46-04:00","published_date":"2022-08-31T08:46:46-04:00","first_published_date":"2022-08-31T08:46:46-04:00","material_type_facet":"Slideshow","kicker":"","subheadline":"","des_facet":null,"org_facet":null,"per_facet":null,"geo_facet":null,"related_urls":[{"suggested_link_text":"Heat, Water, Fire: How Climate Change Is Transforming the Pacific Crest Trail","url":"https://www.nytimes.com/2022/08/31/travel/climate-change-pacific-crest-trail.html"}],"multimedia":[{"url":"https://static01.nyt.com/images/2022/08/31/travel/31travel-pacific-fader-slide-7WCP/31travel-pacific-fader-slide-7WCP-thumbStandard.jpg","format":"Standard Thumbnail","height":75,"width":75,"type":"image","subtype":"photo","caption":"","copyright":"Mason Trinca for The New York Times"},{"url":"https://static01.nyt.com/images/2022/08/31/travel/31travel-pacific-fader-slide-7WCP/31travel-pacific-fader-slide-7WCP-mediumThreeByTwo210.jpg","format":"mediumThreeByTwo210","height":140,"width":210,"type":"image","subtype":"photo","caption":"","copyright":"Mason Trinca for The New York Times"},{"url":"https://static01.nyt.com/images/2022/08/31/travel/31travel-pacific-fader-slide-7WCP/31travel-pacific-fader-slide-7WCP-mediumThreeByTwo440.jpg","format":"mediumThreeByTwo440","height":293,"width":440,"type":"image","subtype":"photo","caption":"","copyright":"Mason Trinca for The New York Times"},{"url":"https://static01.nyt.com/images/2022/08/31/travel/31travel-pacific-fader-slide-7WCP/31travel-pacific-fader-slide-7WCP-articleInline.jpg","format":"Normal","height":127,"width":190,"type":"image","subtype":"photo","caption":"","copyright":"Mason Trinca for The New York Times"}]}"""
        println(Json.decodeFromString<NewsWire>(newsWireJson))
    }

}