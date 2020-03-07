FROM openjdk:12
COPY target/*.jar datastore.jar
ENTRYPOINT ["java", "-jar", "/datastore.jar"]