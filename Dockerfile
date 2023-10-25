# Use the maven:3.8.3-openjdk-17 image as the base image
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the source code and pom.xml from the project root to the container
COPY src /app/src
COPY pom.xml /app

# Build the Spring Boot application using Maven
RUN mvn clean package

# Expose port 3000, which is the port your Spring Boot application listens on
EXPOSE 3000

# Specify the entry point to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "target/pizza_app_backend.jar"]