package org.wit.marketplace.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.marketplace.R
import org.wit.marketplace.main.MainApp

class MarketplaceListActivity : AppCompatActivity(){
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace_list)
        app = application as MainApp
    }
}