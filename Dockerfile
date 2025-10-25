# Use Maven + JDK 17 image
FROM maven:3.9.2-eclipse-temurin-17

# Set working directory inside container
WORKDIR /app

# Copy all project files
COPY . .

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests

# Expose port for Render
EXPOSE 8080

# Start the app (adjust the JAR name if needed)
CMD ["sh", "-c", "java -jar target/*.jar"]
