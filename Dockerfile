FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
EXPOSE 8080
COPY target/DeviceDataSimService.jar DeviceDataSimService.jar
ENTRYPOINT ["java","-jar","/DeviceDataSimService.jar"]
