package com.myretail.inventory.infrastructure.mongo

import com.mongodb.client.model.Filters
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.ProductPrice
import com.myretail.inventory.domain.product.api.ProductPriceRepository
import org.bson.types.ObjectId
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import javax.inject.Inject
import javax.inject.Named

class ProductPriceRepository @Inject constructor(
//  @Named("product-collection") private val mongoCollection: MongoCollection<Price>
 private val mongoCollection: MongoClient
) : ProductPriceRepository {
  override fun find(productId: ProductID): Mono<ProductPrice?> = mongoCollection.getDatabase("product").getCollection("price", Price::class.java)
    .find(Filters.eq("_id", "$productId")).first()
    .toMono()
    .map { it.toDomain() }
}
