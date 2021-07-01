package com.myretail.inventory.application

import com.myretail.inventory.domain.product.Product
import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.ProductName
import com.myretail.inventory.domain.product.api.ProductInfoRepository
import com.myretail.inventory.domain.product.api.ProductPriceRepository
import com.myretail.inventory.domain.product.api.ProductService
import reactor.core.publisher.Mono
import javax.inject.Inject

class ProductService @Inject constructor(
  private val productInfoRepository: ProductInfoRepository,
  private val productPriceRepository: ProductPriceRepository
) : ProductService {
  override fun find(productID: ProductID): Product? {
    return Mono.zip(
      Mono.fromCallable { productInfoRepository.find(productID) },
      productPriceRepository.find(productID)
    ).map {
      val productName = it.t1
      val productPrice = it.t2
      Product(
        productID = productID,
        name = productName as ProductName,
        price = productPrice
      )
    }.block()
  }
}
