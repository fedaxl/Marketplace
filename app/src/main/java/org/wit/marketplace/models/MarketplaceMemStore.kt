package org.wit.marketplace.models

import mu.KotlinLogging
import timber.log.Timber.i

private val logger = KotlinLogging.logger {}
    var lastId = 0L

    internal fun getId(): Long {
        return lastId++
    }

class MarketplaceMemStore : MarketplaceStore {

        val marketItems = ArrayList<MarketplaceModel>()

        override fun findAll(): List<MarketplaceModel> {
            return marketItems
        }


        override fun create(marketItem: MarketplaceModel) {
            marketItems.add(marketItem)
            logAll()
        }

        /*override fun update(marketItem: MarketplaceModel) {
            var foundMarketItem = findOne(marketItem.id!!)
            if (foundMarketItem != null) {
                foundMarketItem.title = marketItem.title
                foundMarketItem.description = marketItem.description
            }
        }*/

        fun logAll() {
            marketItems.forEach { logger.info("${it}") }
        }
    }

}