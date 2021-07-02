package com.myretail.inventory.domain.product.api

import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.ProductPrice

interface ProductPriceRepository {
  fun find(productId: ProductID): ProductPrice?
  fun update(productPrice: ProductPrice)
}
