# Etapa 1: Compilación
FROM gradle:9.2-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

# Etapa 2: Ejecución (Cambiamos la imagen aquí)
FROM openjdk:21-jdk-slim
WORKDIR /app
RUN mkdir ./data
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx350m", "-jar", "app.jar"]