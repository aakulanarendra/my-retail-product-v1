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
import javax.inject.Inject
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [ClassNotFoundException::class, ExceptionHandler::class])
class ClassNotFoundExceptionHandler @Inject constructor(
  appConfig: AppConfig
) : ExceptionHandler<ClassNotFoundException, HttpResponse<ApiError>> {
  private val logger = LoggerFactory.getLogger(javaClass)
  private val codes = appConfig.apiCodes.codes

  override fun handle(request: HttpRequest<*>, e: ClassNotFoundException): HttpResponse<ApiError> {
    logger.error("log_type=server_error, message={}", e.message)

    return serverError(ApiError(5000002, "${codes[5000002]} : ${e.message ?: e.cause?.message}"))
  }
}
