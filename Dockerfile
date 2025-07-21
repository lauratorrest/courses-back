FROM openjdk:17-jdk-alpine
COPY build/libs/*.jar courseya.jar
ENTRYPOINT ["java", "-jar", "courseya.jar"]
