package com.ndup_esiee.osnews.repository

import com.ndup_esiee.osnews.objectgraph.repositoryModule
import com.ndup_esiee.osnews.repository.model.Section
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

@OptIn(ExperimentalCoroutinesApi::class)
class NYTRepositoryTest: KoinTest {

    private val repo: INYTRepository by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(repositoryModule)
    }

    @Test
    fun `check for sections result`() = runTest {
        val sections = repo.getSections()
        // Just check result is well deserialized
        println(sections.map { it.displayName })
        assertEquals(50, sections.count())
    }

    @Test
    fun `check for news wire results`() = runTest {
        val newsWires = repo.getNews()
        println(newsWires.map { it.title })
        assertEquals(20, newsWires.count())
    }

    @Test
    fun `check if requested section is correct`() = runTest {
        val moviesSection = Section("movies", "Movies")
        val newsWires = repo.getNews(moviesSection)
        println(newsWires.map { it.section })
        val newsWieSectionsSet = newsWires.map { it.section }.toSet()
        assertEquals(moviesSection.displayName, newsWieSectionsSet.single())
    }
}