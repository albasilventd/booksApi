FROM openjdk:21 AS builder
 
WORKDIR /hexagonal-arquitecture

COPY ./ /hexagonal-arquitecture/

RUN ./mvnw package -D skipTests
 
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./target/hexagonal-arquitecture-0.0.1-SNAPSHOT.jar"]