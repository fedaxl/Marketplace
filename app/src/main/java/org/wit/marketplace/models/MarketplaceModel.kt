package org.wit.marketplace.models

data class MarketplaceModel(
    var title: String = "",
    var description: String = "",
    var price: Double = 0.00,
    var status: String = ""
)
