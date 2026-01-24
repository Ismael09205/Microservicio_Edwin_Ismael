# Etapa 1: Construcción
FROM gradle:9.2-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

# Etapa 2: Ejecución
FROM eclipse-temurin:21-jdk-slim
WORKDIR /app
RUN mkdir ./data
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
# El límite de memoria evita que Render mate el proceso en el plan free
ENTRYPOINT ["java", "-Xmx350m", "-jar", "app.jar"]