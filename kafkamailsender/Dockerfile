FROM gradle:8.8.0-jdk17 as builder

WORKDIR /

COPY build.gradle /

COPY settings.gradle /

COPY src src

RUN gradle bootJar

FROM eclipse-temurin:17-jdk-jammy

COPY --from=builder /build/libs/ /build/libs/

EXPOSE 8082

CMD ["java", "-jar", "/build/libs/kafkamailsender-0.0.1-SNAPSHOT.jar"]
