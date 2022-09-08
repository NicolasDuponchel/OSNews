package com.ndup_esiee.osnews.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ndup_esiee.osnews.repository.INYTRepository
import com.ndup_esiee.osnews.repository.model.NewsWire
import com.ndup_esiee.osnews.repository.model.NewsWires
import com.ndup_esiee.osnews.repository.model.Section
import com.ndup_esiee.osnews.repository.model.Sections
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

data class MainModel(
    val sections: Sections,
    val newsWires: NewsWires,
)

interface IMainListener {
    @Composable
    fun getModelAsState(): State<MainModel>

    fun onSectionSelected(section: Section)

    fun onNewsWireSelected(newsWire: NewsWire)
}

fun interface INavigateToNewsStrategy {
    operator fun invoke(url: String?)
}


class MainViewModel @AssistedInject constructor(
    private val initialModel: MainModel,
    private val nytRepository: INYTRepository,
    // Example of injection with a param that needs to be provided by an Activity
    @Assisted private val navigateToNewsStrategy: INavigateToNewsStrategy,
) : ViewModel(),
    IMainListener {

    /**
     * Custom factory allowing the Activity to create VM with param
     */
    @AssistedFactory
    fun interface VMAssistedFactory {
        operator fun invoke(navigateToNewsStrategy: INavigateToNewsStrategy): MainViewModel
    }

    class Factory(
        private val assistedFactory: VMAssistedFactory,
        private val navigateToNewsStrategy: INavigateToNewsStrategy,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = assistedFactory(navigateToNewsStrategy) as T
    }


    private val mutableModel: MutableModel by lazy { MutableModel(initialModel) }

    class MutableModel(private val initialModel: MainModel) : MutableLiveData<MainModel>(initialModel) {

        private val nonNullValue: MainModel get() = value ?: initialModel

        fun updateSections(sections: Sections) = postValue(nonNullValue.copy(sections = sections))

        fun updateNewsWire(newsWires: NewsWires) = postValue(nonNullValue.copy(newsWires = newsWires))
    }

    @Composable
    override fun getModelAsState() = mutableModel.observeAsState(initial = initialModel)

    init {
        Log.d("INJECTION", "MainViewModel instance is $this")
        getSections()
        getNewsWires()
    }

    override fun onSectionSelected(section: Section) = getNewsWires(section)

    override fun onNewsWireSelected(newsWire: NewsWire) = navigateToNewsStrategy(newsWire.relatedUrls?.firstNotNullOfOrNull { it.url })

    private fun getSections() {
        Log.d("VIEW MODEL QUERY", "request sections")
        viewModelScope.launch {
            val sections = nytRepository.getSections()
            Log.d("VIEW MODEL QUERY", "sections received: $sections")
            mutableModel.updateSections(sections)
        }
    }

    private fun getNewsWires(section: Section = DEFAULT_SECTION) {
        Log.d("VIEW MODEL QUERY", "request HOME PAGE news wires")
        viewModelScope.launch {
            val newsWires = nytRepository.getNews(section).sortedByDescending { newsWire -> newsWire.relatedUrls != null }
            Log.d("VIEW MODEL QUERY", "newsWires received: $newsWires")
            mutableModel.updateNewsWire(newsWires)
        }
    }

    companion object {
        private val DEFAULT_SECTION = Section("home page", "Home Page")
    }
}
