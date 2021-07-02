package com.myretail.inventory.ports

import com.myretail.inventory.domain.product.ProductID
import com.myretail.inventory.domain.product.api.ProductService
import io.micronaut.http.HttpResponse.notFound
import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.scheduling.TaskExecutors.IO
import io.micronaut.scheduling.annotation.ExecuteOn
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import javax.inject.Inject

@Controller("/products/{id}")
class ProductController @Inject constructor(private val productService: ProductService) {
  @Get(produces = [MediaType.APPLICATION_JSON])
  @Operation(summary = "Fetches Product Information", description = "Return product details for provided product id")
  @ApiResponses(
    ApiResponse(responseCode = "400", description = "Invalid product id provided"),
    ApiResponse(responseCode = "404", description = "Product not found")
  )
  @Tag(name = "product")
  @ExecuteOn(IO)
  fun find(@PathVariable("id") productID: ProductID): MutableHttpResponse<*> {
    return with(productService.find(productID)?.toRest()) {
      this?.let { product -> ok(product) } ?: notFound()
    }
  }

  @Put(consumes = [MediaType.APPLICATION_JSON])
  @ExecuteOn(IO)
  @Operation(summary = "Updates product price", description = "Updates product price details for provided product id")
  @ApiResponses(
    ApiResponse(responseCode = "400", description = "Invalid Name Supplied"),
    ApiResponse(responseCode = "404", description = "Product not found")
  )
  @Tag(name = "product")
  fun update(@PathVariable("id") productID: ProductID, @Body product: Product): MutableHttpResponse<*> {
    return ok(productService.update(product.toDomain()))
  }
}
