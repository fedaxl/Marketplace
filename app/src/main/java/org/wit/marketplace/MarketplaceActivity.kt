package org.wit.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class MarketplaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        Timber.plant(Timber.DebugTree())
        Timber.i("Marketplace Activity started..")
    }
}