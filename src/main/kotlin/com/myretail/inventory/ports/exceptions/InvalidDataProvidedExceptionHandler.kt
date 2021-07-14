package com.myretail.inventory.ports.exceptions

import com.myretail.inventory.infrastructure.config.AppConfig
import com.myretail.inventory.ports.ApiError
import com.myretail.inventory.ports.InvalidDataProvidedException
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
@Requires(classes = [InvalidDataProvidedException::class, ExceptionHandler::class])
class InvalidDataProvidedExceptionHandler @Inject constructor(
  appConfig: AppConfig
) : ExceptionHandler<InvalidDataProvidedException, HttpResponse<ApiError>> {
  private val logger = LoggerFactory.getLogger(javaClass)
  private val codes = appConfig.apiCodes.codes

  override fun handle(request: HttpRequest<*>, e: InvalidDataProvidedException): HttpResponse<ApiError> {
    logger.warn("log_type=product_not_found, product_number={}", e.productId)

    return notFound(ApiError(2000002, "${codes[2000002]} : ${e.message ?: e.cause?.message}"))
  }
}
