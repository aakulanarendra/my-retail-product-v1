package com.myretail.inventory.application

import com.myretail.inventory.domain.product.Product
import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.api.ProductInfoRepository
import com.myretail.inventory.domain.product.api.ProductPriceRepository
import com.myretail.inventory.domain.product.api.ProductService
import io.micronaut.http.server.exceptions.InternalServerException
import org.slf4j.LoggerFactory
import javax.inject.Inject

class ProductService @Inject constructor(
  private val productInfoRepository: ProductInfoRepository,
  private val productPriceRepository: ProductPriceRepository
) : ProductService {
  private val logger = LoggerFactory.getLogger(javaClass)

  override fun find(productID: ProductID): Product? {
    try {
      val productName = productInfoRepository.find(productID)
      val productPrice = productPriceRepository.find(productID)

      if (productName == null && productPrice == null) {
        return null
      }
      return Product(
        productID = productID,
        name = productName,
        price = productPrice
      )
    } catch (e: Exception) {
      logger.error("log_type=find_product_failed, data={}, error={}", productID, e.message)
      throw InternalServerException(e.message)
    }
  }

  override fun update(product: Product) {
    try {
      productPriceRepository.update(product.price!!)
    } catch (e: Exception) {
      logger.error("log_type=update_product_failed, data={}, error={}", product, e.message)
      throw InternalServerException(e.message)
    }
  }
}
