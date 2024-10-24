FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/ChatBotProject0.0.1-SNAPSHOT.jar ChatBotProject.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar","ChatBotProject.jar"]