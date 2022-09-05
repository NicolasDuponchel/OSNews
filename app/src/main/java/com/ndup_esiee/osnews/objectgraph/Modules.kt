package com.ndup_esiee.osnews.objectgraph

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.ndup_esiee.osnews.presentation.IMainListener
import com.ndup_esiee.osnews.presentation.INavigateToNewsStrategy
import com.ndup_esiee.osnews.presentation.MainModel
import com.ndup_esiee.osnews.presentation.MainViewModel
import com.ndup_esiee.osnews.repository.INYTRepository
import com.ndup_esiee.osnews.repository.NYTRepository
import com.ndup_esiee.osnews.repository.NYTServiceFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single<INYTRepository> { NYTRepository() }
    single { NYTServiceFactory.service }
}

val presentationModule = module {
    single { MainModel(sections = emptyList(), newsWires = emptyList()) }


    viewModel {
        val activity: Activity = it[0]
        val urlNavigationStrategy = INavigateToNewsStrategy { nullableUrl ->
            nullableUrl
                ?.let { url -> activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
                ?: run {
                    val errorMessage = "Not able to open url"
                    Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
                    Log.e("INTERNET", "$errorMessage (=$nullableUrl)")
                }
        }

        MainViewModel(
            initialModel = get(),
            nytRepository = get(),
            navigateToNewsStrategy = urlNavigationStrategy,
        )
    } bind IMainListener::class
}