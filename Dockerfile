# Use Java 21 base image
FROM eclipse-temurin:21-jdk

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Expose port for Spring Boot
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "target/Learnova-0.0.1-SNAPSHOT.jar"]
