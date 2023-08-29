FROM openjdk:17
WORKDIR /planner
COPY target/PlannerForTutor-0.0.1-SNAPSHOT.jar /planner
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "PlannerForTutor-0.0.1-SNAPSHOT.jar"]
