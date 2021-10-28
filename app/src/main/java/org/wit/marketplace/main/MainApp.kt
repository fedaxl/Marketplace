package org.wit.marketplace.main

import android.app.Application
import org.wit.marketplace.models.MarketplaceJSONStore
import org.wit.marketplace.models.MarketplaceMemStore
import org.wit.marketplace.models.MarketplaceStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application(){

    lateinit var marketItems : MarketplaceStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        marketItems = MarketplaceJSONStore(applicationContext)
        i("Marketplace started")
    }
}