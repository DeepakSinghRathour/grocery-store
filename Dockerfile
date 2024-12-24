# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR to the working directory
COPY target/grocery-store.jar app.jar


# Expose the application port
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]