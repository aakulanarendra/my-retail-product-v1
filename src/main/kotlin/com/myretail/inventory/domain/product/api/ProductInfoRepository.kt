package com.myretail.inventory.domain.product.api

import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.ProductName

/**
 * Infrastructure service to read data from product api
 */
interface ProductInfoRepository {
  /**
   * Returns [ProductName] for provided [ProductID]
   */
  fun find(productID: ProductID): ProductName?
}
