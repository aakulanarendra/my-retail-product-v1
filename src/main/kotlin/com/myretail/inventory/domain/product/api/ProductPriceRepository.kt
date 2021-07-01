package com.myretail.inventory.domain.product.api

import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.ProductPrice
import reactor.core.publisher.Mono

interface ProductPriceRepository {
  fun find(productId: ProductID): Mono<ProductPrice?>
}
