FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

ADD ./target/msvc-items-1.0.0.jar msvc-items.jar

ENTRYPOINT [ "java", "-jar", "msvc-items.jar" ]