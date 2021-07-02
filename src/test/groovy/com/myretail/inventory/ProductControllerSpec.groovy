package com.myretail.inventory

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.myretail.inventory.ports.ApiError
import com.myretail.inventory.ports.Product
import com.myretail.inventory.ports.ProductPrice
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import org.bson.Document
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.spock.Testcontainers
import org.testcontainers.utility.DockerImageName
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
@Testcontainers
class ProductControllerSpec extends Specification implements TestPropertyProvider {

  @Inject
  @Client(value = "/", errorType = ApiError.class)
  RxHttpClient client

  @Inject
  @Shared
  MongoClient mongoClient

  @Shared
  MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))

  def setupSpec() {
    Document doc = new Document("_id", 190212)
      .append("amount", new BigDecimal(20))
      .append("currency", "USD")

    Document invalid = new Document("_id", 9623)
      .append("amount", 021)
      .append("currency", "HIU")

    Document updateDoc = new Document("_id", 9765)
      .append("amount", new BigDecimal(18))
      .append("currency", "USD")

    MongoCollection mongoCollection = mongoClient.getDatabase("product").getCollection("price")
    mongoCollection.insertOne(doc)
    mongoCollection.insertOne(invalid)
    mongoCollection.insertOne(updateDoc)
  }

  def "should return sucess #status and #price for #productId"() {
    when:
    HttpRequest request = HttpRequest.GET("/products/${productId}")
    String response = client.toBlocking().retrieve(request)
    then:
    response.contains(price)
    where:
    productId | status        | price
    190212    | HttpStatus.OK | new BigDecimal(20).toString()
  }

  def "should return error #status and #code for #productId"() {
    when:
    HttpRequest request = HttpRequest.GET("/products/${productId}")
    MutableHttpResponse response = client.toBlocking().retrieve(request) as MutableHttpResponse
    then:
    HttpClientResponseException ex = thrown()
    and:
    ex.status == status
    and:
    ex.response.body.get().contains(code)
    where:
    productId | status                           | code
    123       | HttpStatus.NOT_FOUND             | "2000001"
    "dsfd"    | HttpStatus.BAD_REQUEST           | "5000013"
    9623      | HttpStatus.INTERNAL_SERVER_ERROR | "500001"
  }

  def "should update #price for #productId"() {
    given:
    def productPrice = new ProductPrice(new BigDecimal(price), "USD")
    def product = new Product(productId, "TEST", productPrice)
    when:
    HttpRequest request = HttpRequest.PUT("/products/${productId}", product)
    MutableHttpResponse response = client.toBlocking().exchange(request) as MutableHttpResponse
    then:
    response.status == HttpStatus.NO_CONTENT
    and:
    mongoClient
      .getDatabase("product")
      .getCollection("price")
      .find(Filters.eq("_id", productId))
      .first().get("amount") == price
    where:
    productId | price
    9765      | 23
  }

  @Override
  Map<String, String> getProperties() {
    mongoDBContainer.start()
    return [
      "MONGO_HOST": mongoDBContainer.containerIpAddress,
      "MONGO_PORT": mongoDBContainer.getMappedPort(27017).toString()
    ] as Map<String, String>
  }
}
