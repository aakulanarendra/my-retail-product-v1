package com.myretail.inventory.infrastructure.rest

internal fun ProductInfo.toDomain() = product?.item?.productDescription?.title
