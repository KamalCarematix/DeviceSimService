FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/DeviceDataSimService.jar DeviceDataSimService.jar
ENTRYPOINT ["java","-jar","/DeviceDataSimService.jar"]
