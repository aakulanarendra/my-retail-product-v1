package com.myretail.inventory.ports.exceptions

import com.myretail.inventory.infrastructure.config.AppConfig
import com.myretail.inventory.ports.ApiError
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.badRequest
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.slf4j.LoggerFactory
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [SocketTimeoutException::class, ExceptionHandler::class])
class SocketTimeoutExceptionHandler @Inject constructor(
  appConfig: AppConfig
) : ExceptionHandler<SocketTimeoutException, HttpResponse<ApiError>> {
  private val logger = LoggerFactory.getLogger(javaClass)
  private val codes = appConfig.apiCodes.codes

  override fun handle(request: HttpRequest<*>, e: SocketTimeoutException): HttpResponse<ApiError> {
    logger.error("log_type=bad_request, message={}", e.message)

    return badRequest(ApiError(500009, codes[500009] ?: "Unknown error"))
  }
}
