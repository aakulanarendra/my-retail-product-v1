package com.myretail.inventory.infrastructure.rest

import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.ProductName
import com.myretail.inventory.domain.product.api.ProductInfoRepository
import javax.inject.Inject

class ProductInfoRepository @Inject constructor(
  private val productInfoReadClient: ProductInfoReadClient
) : ProductInfoRepository {
  override fun find(productID: ProductID): ProductName? {
    return productInfoReadClient.find(
      productID,
      "taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics",
      "candidate#_blank"
    )?.toDomain()
  }
}
