package com.myretail.inventory.ports.exceptions

import com.myretail.inventory.infrastructure.config.AppConfig
import com.myretail.inventory.ports.ApiError
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.serverError
import io.micronaut.http.annotation.Produces
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [HttpClientResponseException::class, ExceptionHandler::class])
class HttpClientResponseExceptionHandler @Inject constructor(
  appConfig: AppConfig
) : ExceptionHandler<HttpClientResponseException, HttpResponse<ApiError>> {
  private val logger = LoggerFactory.getLogger(javaClass)
  private val codes = appConfig.apiCodes.codes

  override fun handle(request: HttpRequest<*>, e: HttpClientResponseException): HttpResponse<ApiError> {
    logger.error("log_type=http_client_error, status={}, message={}", e.status, e.message)

    return serverError(ApiError(500004, codes[500005] ?: "Unknown error"))
  }
}
