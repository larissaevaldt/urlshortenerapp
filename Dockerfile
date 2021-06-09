FROM maven:3.8.1-jdk-11 AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./
 
# package our application code
RUN mvn clean package -DskipTests 
 
# the second stage of our build will use open jdk 11
FROM openjdk:11
EXPOSE 8080
# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD target/urlshortenerapp-0.0.1-SNAPSHOT.jar /myapi.jar
 
# set the startup command to execute the jar
CMD ["java", "-jar", "/myapi.jar"]