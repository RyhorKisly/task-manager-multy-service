FROM eclipse-temurin:17-jre-focal
MAINTAINER GrigoriyKislyi

ADD ./target/gateway-service-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/gateway-service-0.0.1-SNAPSHOT.jar"]