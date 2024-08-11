# Step 1: Use an official Maven image to build the application
FROM maven:3.8.7-eclipse-temurin-19 AS build

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the Maven project files to the container
COPY pom.xml .
COPY src ./src

# Step 4: Build the application (this will generate a JAR file)
RUN mvn clean package -DskipTests

# Step 5: Use an official JDK image to run the application
FROM eclipse-temurin:19-jdk-alpine

# Step 6: Set the working directory in the new image
WORKDIR /app

# Step 7: Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Step 8: Expose the port your application runs on
EXPOSE 8080

# Step 9: Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
