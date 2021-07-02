package com.myretail.inventory.infrastructure.rest

import com.myretail.inventory.domain.product.ProductID
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import io.micronaut.retry.annotation.Retryable

@Client("\${app.rest.product-info-read-url}")
interface ProductInfoReadClient {
  @Get("/{productID}")
  @Retryable
  fun find(productID: ProductID, @QueryValue("excludes") excludes: String, @QueryValue("key") key: String): ProductInfo?
}