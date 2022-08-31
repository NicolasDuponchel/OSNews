package com.ndup_esiee.osnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.ndup_esiee.osnews.repository.INYTRepository
import com.ndup_esiee.osnews.repository.model.NewsWires
import com.ndup_esiee.osnews.repository.model.Section
import com.ndup_esiee.osnews.repository.model.Sections
import com.ndup_esiee.osnews.repository.ui.NewsWireGrid
import com.ndup_esiee.osnews.repository.ui.SectionCells
import com.ndup_esiee.osnews.repository.ui.newsWiresTestList
import com.ndup_esiee.osnews.repository.ui.sectionsTestList
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

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

            mainContent(sections, newsWires)
        }
    }

    @Composable
    private fun mainContent(
        sections: Sections,
        newsWires: NewsWires
    ) {
//        Column(
//                            modifier = Modifier.fillMaxHeight(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//        ) {
            NewsWireGrid(newsWires, Modifier.fillMaxHeight())
            SectionCells(sections)
//        }
    }

    @Composable
    @Preview
    private fun mainPreview() {
        mainContent(sections = sectionsTestList, newsWires = newsWiresTestList)
    }

    private fun testWebCall() {
        lifecycleScope.launch {
            println(nytRepository.getNews(DEFAULT_SECTION))
        }
    }

    companion object {
        private val DEFAULT_SECTION = Section("home page", "Home Page")
    }


}
