FROM eclipse-temurin:19-jre-focal
MAINTAINER ITAcademy

ADD ./target/task-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/task-service.jar"]

EXPOSE 8080 8089