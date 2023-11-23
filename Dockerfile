FROM eclipse-temurin:17-jdk-alpine
WORKDIR /planner
COPY /target/planner.jar /planner.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "planner.jar"]
