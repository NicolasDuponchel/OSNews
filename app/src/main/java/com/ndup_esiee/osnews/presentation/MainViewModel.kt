package com.ndup_esiee.osnews.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndup_esiee.osnews.repository.INYTRepository
import com.ndup_esiee.osnews.repository.model.NewsWires
import com.ndup_esiee.osnews.repository.model.Section
import com.ndup_esiee.osnews.repository.model.Sections
import kotlinx.coroutines.launch

data class MainModel(
    val sections: Sections,
    val newsWires: NewsWires,
)

interface IMainListener {
    @Composable
    fun getModelAsState(): State<MainModel>
}


class MainViewModel(
    private val initialModel: MainModel,
    private val nytRepository: INYTRepository
) : ViewModel(),
    IMainListener {

    private val mutableModel: MutableLiveData<MainModel> by lazy { MutableLiveData(initialModel) }

    @Composable
    override fun getModelAsState() = mutableModel.observeAsState(initial = initialModel)

    init {
        getSections()
        getHomePageNewsWires()
    }

    private fun getSections() {
        Log.d("VIEW MODEL QUERY", "request sections")
        viewModelScope.launch {
            val sections = nytRepository.getSections()
            Log.d("VIEW MODEL QUERY", "sections received: $sections")
            mutableModel.postValue(mutableModel.value!!.copy(sections = sections))
        }
    }

    private fun getHomePageNewsWires() {
        Log.d("VIEW MODEL QUERY", "request HOME PAGE news wires")
        viewModelScope.launch {
            val newsWires = nytRepository.getNews(DEFAULT_SECTION)
            Log.d("VIEW MODEL QUERY", "newsWires received: $newsWires")
            mutableModel.postValue(mutableModel.value!!.copy(newsWires = newsWires))
        }
    }

    //TODO NDU: try to factorise model update properly

    companion object {
        private val DEFAULT_SECTION = Section("home page", "Home Page")
    }
}
