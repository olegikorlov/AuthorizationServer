FROM maven:3.8.1-jdk-11 AS MAVEN_BUILD

COPY ./ ./

RUN mvn clean package -DskipTests

FROM openjdk:11-oracle

COPY --from=MAVEN_BUILD /target/authorizationserver-0.0.1-SNAPSHOT.jar /authorizationserver.jar

CMD ["java", "-jar", "/authorizationserver.jar"]

EXPOSE 8081



