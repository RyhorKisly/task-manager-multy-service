FROM eclipse-temurin:19-jre-focal
MAINTAINER ITAcademy

ADD ./build/libs/task-service-0.1.0.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/task-service-0.1.0.jar"]

EXPOSE 8080 8089