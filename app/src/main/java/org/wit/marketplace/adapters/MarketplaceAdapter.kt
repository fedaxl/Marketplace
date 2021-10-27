package org.wit.marketplace.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.marketplace.databinding.CardMarketplaceBinding
import org.wit.marketplace.models.MarketplaceModel

interface MarketplaceListener {
    fun onMarketplaceClick(marketItem: MarketplaceModel)
}

class MarketplaceAdapter constructor(
    private var marketItems: List<MarketplaceModel>,
    private val listener: MarketplaceListener) :
    RecyclerView.Adapter<MarketplaceAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardMarketplaceBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val marketItem = marketItems[holder.adapterPosition]
        holder.bind(marketItem, listener)
    }

    override fun getItemCount(): Int = marketItems.size

    class MainHolder(private val binding : CardMarketplaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(marketItem: MarketplaceModel, listener: MarketplaceListener) {
            binding.itemTitle.text = marketItem.title
            binding.description.text = marketItem.description
            binding.root.setOnClickListener { listener.onMarketplaceClick(marketItem)
            }
    }
}