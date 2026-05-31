# Paso 1: Compilar la aplicación con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Paso 2: Ejecutar la aplicación con una imagen ligera de Java
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/rutaSalvaje-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]