package com.myretail.inventory.infrastructure.mongo

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Named

@Factory
interface MongoFactory {
  @Bean
  @Named("product-collection")
  fun createCollection(mongoClient: MongoClient): MongoCollection<Price> = mongoClient
    .getDatabase("product")
    .getCollection("price", Price::class.java)
}
