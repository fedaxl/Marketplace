package org.wit.marketplace.models

interface MarketplaceStore {
    fun findAll(): List<MarketplaceModel>
    //fun findOne(id: Long): MarketplaceModel?
    fun create(marketItem: MarketplaceModel)
    fun update(marketItem: MarketplaceModel)
    fun delete(marketItem: MarketplaceModel)
}