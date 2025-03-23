FROM openjdk:17-alpine
WORKDIR /app
COPY build/libs/task-management-system-1.0-SNAPSHOT.jar app.jar
CMD [ "java", "-jar", "app.jar"]