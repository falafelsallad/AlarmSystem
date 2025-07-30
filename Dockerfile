FROM openjdk:21-jdk
EXPOSE 8080
COPY target/AlarmSystem-0.0.1-SNAPSHOT.jar AlarmSystem.jar
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=docker", "AlarmSystem.jar" ]

