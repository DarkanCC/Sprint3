FROM adoptopenjdk/openjdk11
COPY ssmu-api-0.0.1-SNAPSHOT.jar /usr/src/bootdocker/ssmu-api-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/bootdocker
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/bootdocker/ssmu-api-0.0.1-SNAPSHOT.jar"]