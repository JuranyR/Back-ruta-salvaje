# Paso 1: Construir la aplicación (Capa de compilación)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Paso 2: Crear la imagen de ejecución (Aquí estaba el error)
# Usamos Eclipse Temurin que es la imagen oficial recomendada ahora
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copiamos el .jar generado
COPY --from=build /app/target/*.jar app.jar
RUN touch .env
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]