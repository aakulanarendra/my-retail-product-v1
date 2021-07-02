package com.myretail.inventory.application

import com.myretail.inventory.domain.product.Product
import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.api.ProductInfoRepository
import com.myretail.inventory.domain.product.api.ProductPriceRepository
import com.myretail.inventory.domain.product.api.ProductService
import javax.inject.Inject

class ProductService @Inject constructor(
  private val productInfoRepository: ProductInfoRepository,
  private val productPriceRepository: ProductPriceRepository
) : ProductService {
  override fun find(productID: ProductID): Product? {
    val productName = productInfoRepository.find(productID)
    val productPrice = productPriceRepository.find(productID)
    return Product(
      productID = productID,
      name = productName,
      price = productPrice
    )
  }

  override fun update(product: Product) {
    product.price?.let {
      productPriceRepository.update(product.price)
    } ?: throw Exception("")
  }
}
