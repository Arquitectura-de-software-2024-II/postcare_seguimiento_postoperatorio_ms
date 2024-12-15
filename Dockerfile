FROM openjdk:17

WORKDIR /app

ADD target/seguimiento_postoperatorio_ms-0.0.1-SNAPSHOT.jar app.jar

COPY .env .env

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
