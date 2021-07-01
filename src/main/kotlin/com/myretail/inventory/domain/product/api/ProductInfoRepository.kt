package com.myretail.inventory.domain.product.api

import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.ProductName

interface ProductInfoRepository {
  fun find(productID: ProductID): ProductName?
}
