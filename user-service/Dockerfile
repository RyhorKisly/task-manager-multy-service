FROM eclipse-temurin:17-jre-focal
MAINTAINER GrigoriyKislyi
ARG JAR_FILE="build/libs/user-service-0.1.0.jar"
WORKDIR /app
COPY ["${JAR_FILE}", "/app/user-service.jar"]
CMD ["java", "-jar", "/app/user-service.jar"]
EXPOSE 8080 8089