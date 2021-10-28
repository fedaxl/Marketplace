package org.wit.marketplace.main

import android.app.Application
import org.wit.marketplace.models.MarketplaceMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application(){

    val marketItems = MarketplaceMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Marketplace started")
    }
}