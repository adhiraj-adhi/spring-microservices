# Start with a base image containing Java runtime
FROM openjdk:17-ea-17-jdk-slim

# Information around who created and maintains this image
LABEL maintainer="abhinav"

# Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-ms-0.0.1.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "accounts-ms-0.0.1.jar"]