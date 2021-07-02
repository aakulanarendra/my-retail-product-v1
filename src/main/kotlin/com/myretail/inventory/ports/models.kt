package com.myretail.inventory.ports

import com.fasterxml.jackson.annotation.JsonProperty
import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.ProductName
import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.util.Currency
import com.myretail.inventory.domain.product.Product as DomainProduct
import com.myretail.inventory.domain.product.ProductPrice as DomainProductPrice

@Introspected
data class ApiError(val code: Int, val message: String)

@Introspected
data class Health(val message: String, val timestamp: String)

data class Product(
  val id: ProductID,
  val name: ProductName?,
  @JsonProperty("current_price")
  val currentPrice: ProductPrice?
)

data class ProductPrice(
  val value: BigDecimal?,
  @JsonProperty("currency_code")
  val currencyCode: String?
)

internal fun Product.toDomain() = DomainProduct(
  productID = id,
  name = name,
  price = currentPrice?.toDomain(id)
)

internal fun ProductPrice.toDomain(productID: ProductID) = DomainProductPrice(
  amount = value,
  currency = Currency.getInstance(currencyCode),
  productID = productID
)

internal fun DomainProduct.toRest() = Product(
  id = productID,
  name = name,
  currentPrice = price?.toRest()
)

internal fun DomainProductPrice?.toRest() = ProductPrice(
  value = this?.amount,
  currencyCode = this?.currency?.currencyCode
)
