package com.myretail.inventory.infrastructure.rest

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductInfo(
  val product: Product?
)

data class Product(
  val item: Item?
)

data class Item(
  @JsonProperty("product_description")
  val productDescription: ProductDescription?
)

data class ProductDescription(
  val title: String?
)
