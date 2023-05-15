FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8080
ARG APP_NAME="spring-jpa"
ARG APP_VERSION="0.0.1"
ARG JAR_FILE="/target/${APP_NAME}-${APP_VERSION}-SNAPSHOT.jar"

COPY ${JAR_FILE} spring-jpa.jar
ENTRYPOINT ["java","-jar", "spring-jpa.jar"]