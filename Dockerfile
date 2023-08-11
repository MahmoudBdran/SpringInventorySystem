FROM maven:3.6.3-jdk-8 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:18-ea-8-jdk-slim
COPY --from=build /target/InventorySystem-0.0.1-SNAPSHOT.jar InventorySystem.jar
EXPOSE 8080
ENTRYPOINT["java","-jar","InventorySystem.jar"]