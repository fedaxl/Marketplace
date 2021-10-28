package org.wit.marketplace.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.marketplace.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "marketitems.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<MarketplaceModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class MarketplaceJSONStore (private val context: Context) : MarketplaceStore {

    var marketItems = mutableListOf<MarketplaceModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<MarketplaceModel> {
        logAll()
        return marketItems
    }

    override fun create(marketItem: MarketplaceModel) {
        marketItem.id = generateRandomId()
        marketItems.add(marketItem)
        serialize()
    }


    override fun update(marketItem: MarketplaceModel) {
        val marketitemsList = findAll() as ArrayList<MarketplaceModel>
        var foundMarketitem: MarketplaceModel? = marketitemsList.find { p -> p.id == marketItem.id }
        if (foundMarketitem != null) {
            foundMarketitem.title = marketItem.title
            foundMarketitem.description = marketItem.description
            foundMarketitem.image = marketItem.image
            foundMarketitem.lat = marketItem.lat
            foundMarketitem.lng = marketItem.lng
            foundMarketitem.zoom = marketItem.zoom
        }
        serialize()
    }

    override fun delete(marketItem: MarketplaceModel) {
        marketItems.remove(marketItem)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(marketItems, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        marketItems = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        marketItems.forEach { Timber.i("$it") }
    }
}

    class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Uri {
            return Uri.parse(json?.asString)
        }

        override fun serialize(
            src: Uri?,
            typeOfSrc: Type?,
            context: JsonSerializationContext?
        ): JsonElement {
            return JsonPrimitive(src.toString())
        }
    }