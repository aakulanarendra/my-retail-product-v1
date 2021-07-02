package com.myretail.inventory.domain.product.api

import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.ProductPrice

/**
 * Infrastructure service to read price data from mongo db
 */
interface ProductPriceRepository {
  /**
   * Returns [ProductPrice] for provided [ProductID]
   */
  fun find(productId: ProductID): ProductPrice?

  /**
   * updates price data with [ProductPrice]
   */
  fun update(productPrice: ProductPrice)
}
