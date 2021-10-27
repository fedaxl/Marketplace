package org.wit.marketplace.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.marketplace.databinding.ActivityMarketplaceBinding
import org.wit.marketplace.main.MainApp
import org.wit.marketplace.models.MarketplaceModel
import timber.log.Timber
import timber.log.Timber.i

class MarketplaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketplaceBinding
    var marketItem = MarketplaceModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarketplaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp
        i("Marketplace Activity started..")

        binding.btnAdd.setOnClickListener() {
            marketItem.title = binding.itemTitle.text.toString()
            marketItem.description = binding.description.text.toString()

            if (marketItem.title.isNotEmpty()) {
                app.marketItems.add((marketItem.copy()))
                i("add Button Pressed: $marketItem")
                for(i in app.marketItems.indices)
                {i("Item[$i]:${this.app.marketItems[i]}")}
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}