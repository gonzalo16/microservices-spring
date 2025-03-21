FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
EXPOSE 8761

ADD ./target/eureka-server-1.0.0.jar eureka-server.jar

ENTRYPOINT [ "java", "-jar", "eureka-server.jar" ]