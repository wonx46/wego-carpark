FROM openjdk:8-jdk-alpine
COPY test-iwan-0.0.1-SNAPSHOT.jar /test-iwan-0.0.1-SNAPSHOT.jar
COPY config/application.properties /config/application.properties
ENTRYPOINT ["java","-Xms125m","-Xmx125m","-jar","/test-iwan-0.0.1-SNAPSHOT.jar","--spring.config.location=/config/application.properties"]