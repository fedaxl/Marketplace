package org.wit.marketplace.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import org.wit.marketplace.R
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

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        if (intent.hasExtra("item_edit")) {
            marketItem = intent.extras?.getParcelable("item_edit")!!
            binding.itemTitle.setText(marketItem.title)
            binding.description.setText(marketItem.description)
        }

        binding.btnAdd.setOnClickListener() {
            marketItem.title = binding.itemTitle.text.toString()
            marketItem.description = binding.description.text.toString()

            if (marketItem.title.isNotEmpty()) {
                app.marketItems.create((marketItem.copy()))
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar
                    .make(it,R.string.enter_item_title, Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_marketplace, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}