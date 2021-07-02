package com.myretail.inventory.ports.exceptions

import com.myretail.inventory.infrastructure.config.AppConfig
import com.myretail.inventory.ports.ApiError
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.serverError
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [TimeoutException::class, ExceptionHandler::class])
class TimeoutExceptionHandler @Inject constructor(
  appConfig: AppConfig
) : ExceptionHandler<TimeoutException, HttpResponse<ApiError>> {
  private val logger = LoggerFactory.getLogger(javaClass)
  private val codes = appConfig.apiCodes.codes

  override fun handle(request: HttpRequest<*>, e: TimeoutException): HttpResponse<ApiError> {
    logger.error("log_type=server_error, message={}", e.message)

    return serverError(ApiError(5000010, codes[5000010] ?: "Unknown error"))
  }
}
