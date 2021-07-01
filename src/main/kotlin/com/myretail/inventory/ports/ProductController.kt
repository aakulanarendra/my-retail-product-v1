package com.myretail.inventory.ports

import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.api.ProductService
import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.scheduling.TaskExecutors.IO
import io.micronaut.scheduling.annotation.ExecuteOn
import javax.inject.Inject

@Controller("products/{id}")
class ProductController @Inject constructor(private val productService: ProductService) {
  @Get
  @ExecuteOn(IO)
  fun find(@PathVariable("id") productID: ProductID): MutableHttpResponse<*>{
    return ok(productService.find(productID))
  }
}