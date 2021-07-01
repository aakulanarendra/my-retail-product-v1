package com.myretail.inventory.infrastructure.mongo

import com.myretail.inventory.domain.product.ProductPrice as DomainProductPrice

internal fun Price.toDomain() = DomainProductPrice(
  productID = productID,
  price = value,
  currency = currency
)
