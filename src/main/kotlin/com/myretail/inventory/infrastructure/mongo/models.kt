package com.myretail.inventory.infrastructure.mongo

import org.bson.codecs.pojo.annotations.BsonDiscriminator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import java.math.BigDecimal

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class NoArg

@NoArg
@BsonDiscriminator
data class Price(
  @BsonId
  var _id: Long,
  @BsonProperty("amount")
  var amount: BigDecimal?,
  @BsonProperty("currency")
  var currency: String?
) {
  constructor() : this(0, BigDecimal.ZERO, "")
}
