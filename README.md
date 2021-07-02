# MyRetail Product API

Service returns product information for provided product id and updated price information for provided product id

##About
1. API is developed on [**Micronaut**](https://micronaut.io/) in [**Kotlin**](https://kotlinlang.org/) language on [**Domain Driven Architecture**](https://en.wikipedia.org/wiki/Domain-driven_design)
2. Used [Mongodb](https://www.mongodb.com/) as a service (Saas)
3. Containerization using Docker
4. CICD (Jenkins/GithubActions)
5. Deployed on [Azure](https://azure.microsoft.com/en-us/features/azure-portal/)

## Live Demo

[Api Docs](http://20.84.178.141:8080/swagger-ui)

## Pre Requisites

- [Open JDK 11](https://adoptopenjdk.net/)
- [Gradle](https://docs.gradle.org/current/userguide/gradle_wrapper.html)
- [Docker](https://hub.docker.com) (Optional)

---

## Run Locally

1. Clone repo from github
   ```bash 
   git clone https://github.com/aakulanarendra/my-retail-product-v1.git 
   ```
2. Navigate root folder and run gradlew run
   ```bash
   ./gradlew run
   ```
3. Service should be up and running in 8080 and try accessing `http://localhost:8080`

## Quickstart

- Pull the image `aakulanarendra/myretail:latest` from [docker hub](https://hub.docker.com/repository/docker/aakulanarendra/myretail)
- Run the image

## API Documentation

We can browse api docs generated by different utils from below apis

- {host}/swagger-ui
- {host}/redoc
- {host}/rapidoc

## Monitoring

## Test Cases
```bash
   ./gradlew clean test
   ```

