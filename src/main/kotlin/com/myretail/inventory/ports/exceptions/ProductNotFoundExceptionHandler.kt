package com.myretail.inventory.ports.exceptions

import com.myretail.inventory.infrastructure.config.AppConfig
import com.myretail.inventory.ports.ApiError
import com.myretail.inventory.ports.ProductNotFoundException
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.notFound
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [ProductNotFoundException::class, ExceptionHandler::class])
class ProductNotFoundExceptionHandler @Inject constructor(
  appConfig: AppConfig
) : ExceptionHandler<ProductNotFoundException, HttpResponse<ApiError>> {
  private val logger = LoggerFactory.getLogger(javaClass)
  private val codes = appConfig.apiCodes.codes

  override fun handle(request: HttpRequest<*>, e: ProductNotFoundException): HttpResponse<ApiError> {
    logger.warn("log_type=product_not_found, product_number={}", e.productId)

    return notFound(ApiError(2000001, codes[2000001] ?: "Unknown error"))
  }
}
