package com.ndup_esiee.osnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ndup_esiee.osnews.repository.INYTRepository
import com.ndup_esiee.osnews.repository.model.NewsWires
import com.ndup_esiee.osnews.repository.model.Section
import com.ndup_esiee.osnews.repository.model.Sections
import com.ndup_esiee.osnews.ui.NewsWireGrid
import com.ndup_esiee.osnews.ui.SectionCells
import com.ndup_esiee.osnews.ui.newsWiresTestList
import com.ndup_esiee.osnews.ui.sectionsTestList
import org.koin.android.ext.android.inject

/**
 * TODO NDU: FIX main issue --> []retrofit2.HttpException: HTTP 429 Too Many Requests]
 */
class MainActivity : ComponentActivity() {

    private val nytRepository: INYTRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var sections: Sections by remember { mutableStateOf(emptyList()) }
            LaunchedEffect(sections) { sections = nytRepository.getSections() }

            var newsWires: NewsWires by remember { mutableStateOf(emptyList()) }
            LaunchedEffect(newsWires) {
                newsWires = nytRepository.getNews(DEFAULT_SECTION)
                println(newsWires)
            }

            Layout(
                sections = sections,
                newsWires = newsWires,
            )
        }
    }

    @Composable
    private fun Layout(
        modifier: Modifier = Modifier,
        sections: Sections,
        newsWires: NewsWires,
    ) {
        BoxWithConstraints(
            modifier = modifier,
            contentAlignment = Alignment.BottomCenter
        ) {
            NewsWireGrid(newsWires, Modifier.fillMaxHeight())
            SectionCells(sections)
        }
    }

    @Composable
    @Preview
    private fun mainPreview() {
        Layout(sections = sectionsTestList, newsWires = newsWiresTestList)
    }


    companion object {
        private val DEFAULT_SECTION = Section("home page", "Home Page")
    }


}
