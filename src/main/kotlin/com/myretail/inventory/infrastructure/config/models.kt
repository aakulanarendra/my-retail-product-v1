package com.myretail.inventory.infrastructure.config

import io.micronaut.context.annotation.ConfigurationProperties
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppConfig @Inject constructor(
  val apiCodes: ApiCodesConfig
)

@ConfigurationProperties("app.api-codes")
interface ApiCodesConfig {
  val codes: Map<Int, String>
}
