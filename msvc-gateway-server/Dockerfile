FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
EXPOSE 8090

ADD ./target/msvc-gateway-server-1.0.0.jar msvc-gateway-server.jar

ENTRYPOINT [ "java", "-jar", "msvc-gateway-server.jar" ]