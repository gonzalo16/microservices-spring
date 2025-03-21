FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

ADD ./target/msvc-products-1.0.0.jar msvc-products.jar

ENTRYPOINT [ "java", "-jar", "msvc-products.jar" ]