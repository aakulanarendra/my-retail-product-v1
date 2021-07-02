package com.myretail.inventory.domain.product.api

import com.myretail.inventory.domain.product.Product
import com.myretail.inventory.domain.product.ProductID

/**
 * Domain service to read/update data from product api
 */
interface ProductService {
  /**
   * Returns [Product] for provided [ProductID]
   */
  fun find(productID: ProductID): Product?

  /**
   * updates price data with [Product]
   */
  fun update(product: Product)
}
