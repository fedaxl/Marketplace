package org.wit.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.marketplace.databinding.ActivityMarketplaceBinding
import timber.log.Timber
import timber.log.Timber.i

class MarketplaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketplaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarketplaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        i("Marketplace Activity started..")

        binding.btnAdd.setOnClickListener() {
            val itemTitle = binding.itemTitle.text.toString()
            if (itemTitle.isNotEmpty()) {
                i("add Button Pressed: $itemTitle")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}