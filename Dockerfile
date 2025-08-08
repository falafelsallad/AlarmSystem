FROM openjdk:21-jdk
EXPOSE 8080
COPY target/AlarmSystem-0.0.1-SNAPSHOT.jar AlarmSystem.jar
ENTRYPOINT [ "java", "-jar", "AlarmSystem.jar" ]
# build image by running this in the terminal .\build-docker.bat
# "-Dspring.profiles.active=docker",

