# the base image - jdk amazon v17 linux
FROM amazoncorretto:17

# the JAR file path
ARG JAR_FILE=jar/*.jar

COPY ${JAR_FILE} application.jar

CMD apt-get update -y

# Set the default command to run the Java application
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]