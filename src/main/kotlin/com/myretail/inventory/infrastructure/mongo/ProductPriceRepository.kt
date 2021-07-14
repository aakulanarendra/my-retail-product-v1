package com.myretail.inventory.infrastructure.mongo

import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates.combine
import com.mongodb.client.model.Updates.set
import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.ProductPrice
import com.myretail.inventory.domain.product.api.ProductPriceRepository
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import javax.inject.Inject

class ProductPriceRepository @Inject constructor(
  private val mongoClient: MongoClient
) : ProductPriceRepository {

  private val codecRegistry: CodecRegistry = fromRegistries(
    MongoClientSettings.getDefaultCodecRegistry(),
    fromProviders(
      PojoCodecProvider.builder()
        .automatic(true)
        .build()
    )
  )

  override fun find(productId: ProductID): ProductPrice? =
    getCollection()
      .find(eq("_id", productId)).first()?.toDomain(productId)

  override fun update(productPrice: ProductPrice) {
    with(productPrice.toMongo()) {
      getCollection()
        .updateOne(
          eq("_id", productPrice.productID),
          combine(set("amount", amount), set("currency", currency)),
          UpdateOptions().upsert(true)
        )
    }
  }

  private fun getCollection() = mongoClient
    .getDatabase("product")
    .getCollection("price", Price::class.java)

}
