FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV SERVER_PORT=8080
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:discipline.db

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
