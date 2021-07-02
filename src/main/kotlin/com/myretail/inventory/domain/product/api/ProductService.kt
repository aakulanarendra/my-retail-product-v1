package com.myretail.inventory.domain.product.api

import com.myretail.inventory.domain.product.Product
import com.myretail.inventory.domain.product.ProductID

interface ProductService {
  fun find(productID: ProductID): Product?
  fun update(product: Product)
}
