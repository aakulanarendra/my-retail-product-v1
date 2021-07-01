package com.myretail.inventory.infrastructure.mongo

import com.myretail.inventory.domain.product.ProductID
import java.math.BigDecimal
import java.util.Currency

data class Price(
  val productID: ProductID,
  val value: BigDecimal,
  val currency: Currency
)
