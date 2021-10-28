package org.wit.marketplace.models

import mu.KotlinLogging
import timber.log.Timber.i

//private val logger = KotlinLogging.logger {}
    var lastId = 0L

    internal fun getId(): Long {
        return lastId++
    }

class MarketplaceMemStore : MarketplaceStore {

        val marketItems = ArrayList<MarketplaceModel>()

        override fun findAll(): List<MarketplaceModel> {
            return marketItems
        }

        /*override fun findById(id:Long) : MarketplaceModel? {
        val foundMarketItem: MarketplaceModel? = marketItems.find { it.id == id }
        return foundMarketItem
        }*/

        override fun create(marketItem: MarketplaceModel) {
            marketItem.id = getId()
            marketItems.add(marketItem)
            logAll()
        }

        override fun update(marketItem: MarketplaceModel) {
        var foundMarketItem: MarketplaceModel? = marketItems.find { p -> p.id == marketItem.id }
        if (foundMarketItem != null) {
            foundMarketItem.title = marketItem.title
            foundMarketItem.description = marketItem.description
            foundMarketItem.image = marketItem.image
            logAll()
            }
        }

        override fun delete(marketItem: MarketplaceModel) {
            marketItem.id = getId()
            marketItems.remove(marketItem)
            logAll()
        }

        private fun logAll() {
            marketItems.forEach { i("$it") }
        }
    }