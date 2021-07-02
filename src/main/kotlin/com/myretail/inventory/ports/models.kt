package com.myretail.inventory.ports

import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.ProductName
import java.math.BigDecimal
import java.util.Currency
import com.myretail.inventory.domain.product.Product as DomainProduct
import com.myretail.inventory.domain.product.ProductPrice as DomainProductPrice

data class Product(
  val id: ProductID,
  val name: ProductName?,
  val current_price: ProductPrice?
)

data class ProductPrice(
  val value: BigDecimal?,
  val currency_code: String?
)

internal fun Product.toDomain() = DomainProduct(
  productID = id,
  name = name,
  price = current_price?.toDomain(id)
)

internal fun ProductPrice.toDomain(productID: ProductID) = DomainProductPrice(
  amount = value,
  currency = Currency.getInstance(currency_code),
  productID = productID
)

internal fun DomainProduct.toRest() = Product(
  id = productID,
  name = name,
  current_price = price?.toRest()
)

internal fun DomainProductPrice?.toRest() = ProductPrice(
  value = this?.amount,
  currency_code = this?.currency?.currencyCode
)
