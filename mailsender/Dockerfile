FROM gradle:8.8.0-jdk17 as builder

COPY /build.gradle /home/gradle/

COPY /settings.gradle /home/gradle/

COPY /src /src

RUN gradle build

RUN gradle bootJar

FROM eclipse-temurin:17-jdk-jammy

COPY --from=builder /builds/libs/ /build/libs/

EXPOSE 8081

CMD ["java", "-jar", "/build/libs/mailsender-0.0.1-SNAPSHOT.jar"]
