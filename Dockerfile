FROM openjdk:11
EXPOSE 8080
ADD /target/urlshortenerapp-0.0.1-SNAPSHOT.jar myapi.jar
ENTRYPOINT ["java","-jar","myapi.jar"]