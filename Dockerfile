FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskilTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/paisa-Management-0.0.1-SNAPSHOT.jar paisa-Management.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","paisa-Management.jar"]