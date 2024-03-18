# Use the official OpenJDK base image
FROM openjdk:23-slim-bullseye

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Java application JAR file into the container at /app
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

# Expose port 8080
EXPOSE 8080

# Command to run your Java application
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]