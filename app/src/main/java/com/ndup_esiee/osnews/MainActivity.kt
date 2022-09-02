package com.ndup_esiee.osnews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ndup_esiee.osnews.presentation.IMainListener
import com.ndup_esiee.osnews.repository.model.NewsWire
import com.ndup_esiee.osnews.repository.model.NewsWires
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

    private val viewModel: IMainListener by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainModelAsState = viewModel.getModelAsState()
            Layout(
                sections = mainModelAsState.value.sections,
                newsWires = mainModelAsState.value.newsWires,
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
            NewsWireGrid(newsWires, Modifier.fillMaxHeight()) { openNewsInBrowser(it) }
            SectionCells(sections) { viewModel.onSectionSelected(it) }
        }
    }

    private fun openNewsInBrowser(news: NewsWire) {
        (news.relatedUrls?.firstOrNull()?.url
            ?.let { url -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
            ?: run {
                val errorMessage = "Not able to open url"
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                Log.e("INTERNET", "$errorMessage=${news.relatedUrls?.firstOrNull()?.url} in ${news.relatedUrls}")
            })
    }

    @Composable
    @Preview
    private fun mainPreview() {
        Layout(sections = sectionsTestList, newsWires = newsWiresTestList)
    }

}
