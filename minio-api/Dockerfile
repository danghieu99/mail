FROM gradle:8.8.0-jdk17 as builder

COPY /build.gradle /

COPY /settings.gradle /

RUN gradle bootJar

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /

COPY ./build/libs/ /build/libs/

EXPOSE 8080

CMD ["java", "-jar", "./build/libs/minio-api-0.0.1-SNAPSHOT.jar"]


