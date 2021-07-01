package com.myretail.inventory

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
  build()
    .args(*args)
    .packages("com.myretail.inventory")
    .start()
}
