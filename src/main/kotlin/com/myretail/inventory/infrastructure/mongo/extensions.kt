package com.myretail.inventory.infrastructure.mongo

import com.myretail.inventory.domain.product.ProductID
import java.util.Currency
import com.myretail.inventory.domain.product.ProductPrice as DomainProductPrice

internal fun Price.toDomain(productID: ProductID) = DomainProductPrice(
  productID = productID,
  amount = amount,
  currency = Currency.getInstance(currency)
)

internal fun DomainProductPrice.toMongo() = Price(
  _id = productID,
  amount = amount,
  currency = currency?.currencyCode
)
