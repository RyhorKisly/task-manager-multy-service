FROM eclipse-temurin:17-jre-focal
MAINTAINER GrigoriyKislyi
ARG JAR_FILE="build/libs/task-service-0.1.0.jar"
WORKDIR /app
COPY ["${JAR_FILE}", "/app/task-service.jar"]
CMD ["java", "-jar", "/app/task-service.jar"]
EXPOSE 8080 8089