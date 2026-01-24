
FROM gradle:9.2-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
RUN mkdir ./data
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-Xmx350m", "-jar", "app.jar"]