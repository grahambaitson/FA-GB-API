FROM java:openjdk-8-jre-alpine
MAINTAINER Graham Baitson <grahambaitson@gmail.com.com>

WORKDIR /opt/fa

COPY build/libs lib

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "lib/FA-GB-API-0.1.0.jar"]
