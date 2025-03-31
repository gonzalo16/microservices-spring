FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
EXPOSE 9100
ADD ./target/msvc-oauth-1.0.0.jar msvc-oauth.jar

ENTRYPOINT [ "java", "-jar", "msvc-oauth.jar" ]