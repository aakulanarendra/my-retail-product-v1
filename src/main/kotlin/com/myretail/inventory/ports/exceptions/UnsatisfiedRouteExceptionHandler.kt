package com.myretail.inventory.ports.exceptions

import com.myretail.inventory.infrastructure.config.AppConfig
import com.myretail.inventory.ports.ApiError
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.badRequest
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.web.router.exceptions.UnsatisfiedRouteException
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [UnsatisfiedRouteException::class, ExceptionHandler::class])
class UnsatisfiedRouteExceptionHandler @Inject constructor(
  appConfig: AppConfig
) : ExceptionHandler<UnsatisfiedRouteException, HttpResponse<ApiError>> {
  private val logger = LoggerFactory.getLogger(javaClass)
  private val codes = appConfig.apiCodes.codes

  override fun handle(request: HttpRequest<*>, e: UnsatisfiedRouteException): HttpResponse<ApiError> {
    logger.error("log_type=bad_request, message={}", e.message)

    return badRequest(ApiError(5000012, "${codes[5000012]} : ${e.message ?: e.cause?.message}"))
  }
}
