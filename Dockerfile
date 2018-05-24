FROM openjdk:8-jdk-alpine

MAINTAINER Laijin Lu <1@aikin.me>

COPY build/libs/bicyclestore-user-service.jar /app/bicyclestore-user-service.jar

WORKDIR /app

CMD ["java", "-jar", "-Xmx2048m", "bicyclestore-user-service.jar"]
