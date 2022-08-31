package com.ndup_esiee.osnews.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NYTRepositoryTest {

    //TODO NDU: inject this ?
    private val repo = NYTRepository()

    @Test
    fun `check for sections result`() = runTest {
        val expectedSections = listOf(
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
        val sections = repo.getSections()
        assertEquals(expectedSections, sections.map { it.displayName })
        assertEquals(50, sections.count())
    }
}