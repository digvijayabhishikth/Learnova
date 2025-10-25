# Use OpenJDK 21 for build
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build the project
RUN ./mvnw clean package -DskipTests
