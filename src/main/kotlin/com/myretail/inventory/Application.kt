package com.myretail.inventory

import io.micronaut.runtime.Micronaut.build
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
  info = Info(
    title = "MyRetail",
    version = "0.0",
    description = "Product API",
    contact = Contact(url = "http://www.narendraakula.com", name = "Narendra", email = "aakulanarendra91@gmail.com")
  )
)
object Api {
}

fun main(args: Array<String>) {
  build()
    .args(*args)
    .packages("com.myretail.inventory")
    .start()
}
