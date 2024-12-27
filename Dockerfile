# Etapa de construcción
FROM maven:3.9.4-eclipse-temurin-21 AS BUILDER

WORKDIR /app

# Copiar archivos necesarios para la construcción
COPY pom.xml mvnw ./
COPY .mvn/ .mvn
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copiar el código fuente y construir el proyecto
COPY src ./src
RUN ./mvnw clean install -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21.0.2_13-jre-jammy AS RUNNER

WORKDIR /app

# Exponer el puerto para la aplicación
EXPOSE 8080

# Copiar el archivo JAR generado desde la etapa de construcción
COPY --from=BUILDER /app/target/*.jar /app/app.jar

# Comando de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
