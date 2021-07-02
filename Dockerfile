FROM openjdk:11.0.11-jdk-oracle
COPY build/libs/myretail-product-v1-*-all.jar myretail-product-v1.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "myretail-product-v1.jar"]
