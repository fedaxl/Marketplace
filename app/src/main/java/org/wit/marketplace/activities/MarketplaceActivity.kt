package org.wit.marketplace.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.marketplace.R
import org.wit.marketplace.databinding.ActivityMarketplaceBinding
import org.wit.marketplace.helpers.showImagePicker
import org.wit.marketplace.main.MainApp
import org.wit.marketplace.models.Location
import org.wit.marketplace.models.MarketplaceModel
import timber.log.Timber.i

class MarketplaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketplaceBinding
    var marketItem = MarketplaceModel()
    lateinit var app : MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    val IMAGE_REQUEST = 1
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    var location = Location(52.245696, -7.139102, 15f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var edit = false

        binding = ActivityMarketplaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        i("Marketplace Activity started...")

        if (intent.hasExtra("item_edit")) {
            edit = true
            marketItem = intent.extras?.getParcelable("item_edit")!!
            binding.itemTitle.setText(marketItem.title)
            binding.description.setText(marketItem.description)
            binding.btnAdd.setText(R.string.save_item)
            Picasso.get()
                .load(marketItem.image)
                .into(binding.itemImage)
            if (marketItem.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_item_image)
            }
        }

        binding.btnAdd.setOnClickListener() {
            marketItem.title = binding.itemTitle.text.toString()
            marketItem.description = binding.description.text.toString()

            if (marketItem.title.isEmpty()) {
                Snackbar.make(it, R.string.enter_item_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.marketItems.update(marketItem.copy())
                } else {
                    app.marketItems.create(marketItem.copy())
                }
            }
            i("add Button Pressed: $marketItem")
            setResult(RESULT_OK)
            finish()
        }
        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        binding.itemLocation.setOnClickListener {
            val launcherIntent = Intent(this, MapsActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }

        registerImagePickerCallback()
        registerMapCallback()
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

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            marketItem.image = result.data!!.data!!
                            Picasso.get()
                                .load(marketItem.image)
                                .into(binding.itemImage)
                            binding.chooseImage.setText(R.string.change_item_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            location = result.data!!.extras?.getParcelable("location")!!
                            i("Location == $location")
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

}