package com.myretail.inventory.infrastructure.mongo

import java.util.Currency
import com.myretail.inventory.domain.product.ProductPrice as DomainProductPrice

internal fun Price.toDomain() = DomainProductPrice(
  productID = _id,
  amount = amount,
  currency = Currency.getInstance(currency)
)

internal fun DomainProductPrice.toMongo() = Price(
  _id = productID,
  amount = amount,
  currency = currency?.currencyCode
)
