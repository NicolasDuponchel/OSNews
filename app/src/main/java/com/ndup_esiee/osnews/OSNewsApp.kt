package com.ndup_esiee.osnews

import android.app.Application
import com.ndup_esiee.osnews.objectgraph.presentationModule
import com.ndup_esiee.osnews.objectgraph.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OSNewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@OSNewsApp)
            modules(repositoryModule, presentationModule)
        }
    }
}