FROM eclipse-temurin:19-jre-focal
MAINTAINER GrigoriyKislyi

ADD ./target/audit-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/audit-service.jar"]

EXPOSE 8080 8089