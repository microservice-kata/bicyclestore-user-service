FROM java:8-jre

MAINTAINER Laijin Lu <1@aikin.me>

COPY build/libs/bicyclestore-user-service.jar /app/bicyclestore-user-service.jar

WORKDIR /app

CMD java -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -verbose:gc -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationStoppedTime -Xloggc:gc_$(date +%Y%m%d-%H%M%S).log -jar bicyclestore-user-service.jar
