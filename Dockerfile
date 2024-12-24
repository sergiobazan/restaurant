FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY target/restaurant-0.0.1-SNAPSHOT.jar /app/restaurant-app.jar
EXPOSE 8080
CMD ["java", "-jar", "restaurant-app.jar"]