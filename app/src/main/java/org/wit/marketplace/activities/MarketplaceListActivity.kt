package org.wit.marketplace.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, MarketplaceActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMarketplaceClick(marketItem: MarketplaceModel) {
        val launcherIntent = Intent(this, MarketplaceActivity::class.java)
        launcherIntent.putExtra("item_edit", marketItem)
        startActivityForResult(launcherIntent,0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

}

