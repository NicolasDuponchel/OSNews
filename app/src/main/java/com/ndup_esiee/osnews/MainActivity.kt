package com.ndup_esiee.osnews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ndup_esiee.osnews.presentation.IMainListener
import com.ndup_esiee.osnews.presentation.INavigateToNewsStrategy
import com.ndup_esiee.osnews.presentation.MainViewModel
import com.ndup_esiee.osnews.repository.model.NewsWires
import com.ndup_esiee.osnews.repository.model.Sections
import com.ndup_esiee.osnews.ui.NewsWireGrid
import com.ndup_esiee.osnews.ui.SectionCells
import com.ndup_esiee.osnews.ui.newsWiresTestList
import com.ndup_esiee.osnews.ui.sectionsTestList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModel.VMAssistedFactory

    private val mainListener: IMainListener by viewModels {
        MainViewModel.Factory(mainViewModelFactory, navigationStrategy)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainModelAsState = mainListener.getModelAsState().value
            Layout(
                sections = mainModelAsState.sections,
                newsWires = mainModelAsState.newsWires,
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
            NewsWireGrid(newsWires, Modifier.fillMaxHeight()) { mainListener.onNewsWireSelected(it) }
            SectionCells(sections) { mainListener.onSectionSelected(it) }
        }
    }

    @Composable
    @Preview
    private fun mainPreview() {
        Layout(sections = sectionsTestList, newsWires = newsWiresTestList)
    }

    private val navigationStrategy by lazy {
        INavigateToNewsStrategy { nullableUrl ->
            nullableUrl
                ?.let { url -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
                ?: run {
                    val errorMessage = "Not able to open url"
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                    Log.e("INTERNET", "$errorMessage (=$nullableUrl)")
                }
        }
    }

}
