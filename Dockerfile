FROM eclipse-temurin:21-alpine as BUILDER

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY /src ./src
RUN ./mvnw clean install

FROM eclipse-temurin:21.0.2_13-jre-jammy as RUNNER

WORKDIR /app

EXPOSE 8080
COPY --from=BUILDER /app/target/*.jar /app/*.jar
ENTRYPOINT ["java", "-jar", "/app/*.jar"]