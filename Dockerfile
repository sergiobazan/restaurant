FROM eclipse-temurin:21-alpine as builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline
COPY ./src ./src
RUN chmod +x mvnw && ./mvnw clean install

FROM eclipse-temurin:21.0.2_13-jre-jammy as final
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/target/*.jar /app/*.jar
ENTRYPOINT ["java", "-jar", "/app/*.jar"]