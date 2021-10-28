package org.wit.marketplace.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.marketplace.R
import org.wit.marketplace.adapters.MarketplaceAdapter
import org.wit.marketplace.adapters.MarketplaceListener
import org.wit.marketplace.databinding.ActivityMarketplaceListBinding
import org.wit.marketplace.main.MainApp
import org.wit.marketplace.models.MarketplaceModel

class MarketplaceListActivity : AppCompatActivity(), MarketplaceListener {
    lateinit var app: MainApp
    private lateinit var binding: ActivityMarketplaceListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketplaceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = MarketplaceAdapter(app.marketItems.findAll(), this)

        registerRefreshCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, MarketplaceActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMarketplaceClick(marketItem: MarketplaceModel) {
        val launcherIntent = Intent(this, MarketplaceActivity::class.java)
        launcherIntent.putExtra("item_edit", marketItem)
        refreshIntentLauncher.launch(launcherIntent)
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }*/

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { binding.recyclerView.adapter?.notifyDataSetChanged() }
    }

}

