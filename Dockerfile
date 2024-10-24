# Step 1: Use an official OpenJDK runtime as a parent image
FROM amazoncorretto:17.0.7-alpine3.17

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the build files from the host machine to the container
COPY 'build/libs/soundary-api-server-0.0.1-SNAPSHOT.jar' app.jar

# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]