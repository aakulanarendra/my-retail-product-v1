package com.myretail.inventory.domain.product

import java.math.BigDecimal
import java.util.Currency

typealias ProductID = Long
typealias ProductName = String

data class Product(
  val productID: ProductID,
  val name: ProductName?,
  val price: ProductPrice?
)

data class ProductPrice(
  val productID: ProductID,
  val amount: BigDecimal?,
  val currency: Currency?
)
