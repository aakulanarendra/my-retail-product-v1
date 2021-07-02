package com.myretail.inventory.ports

import com.myretail.inventory.domain.product.ProductID
import io.micronaut.core.type.Argument

class HealthCheckException : Exception()
class MissingParameterException internal constructor(val argument: Argument<*>) : Exception()
class ProductNotFoundException(val productId: ProductID) : Exception("$productId")
class InvalidProductIDException(val productId: ProductID) : Exception("$productId")
class InvalidDataProvidedException(val productId: ProductID) : Exception("$productId")
