# Intro

Based on the requirements I have created a small Spring Boot application.

I would have liked to add more tests but there was some time constraints. 

1. The REST was tested using [rest-test.http](rest-test.http).
2. There is some test data in [test-data.sql](src/main/resources/test-data.sql), but I started using [TestDataCreator.java](src/main/java/nl/pink/pinkproject/util/TestDataCreator.java) instead.
3. I tried to stick to a bit more function java streams apis where possible.

I named two of the REST methods after the 3a and 3b section in the 
Opdracht document.

[Opdracht_-_Java_Developer_-_Pinkroccade_Local_Government.pdf](Opdracht_-_Java_Developer_-_Pinkroccade_Local_Government.pdf)

Thank you for your consideration, as always there are improvements and different ways of completing the same
task, but I would still value some feedback.

# Building and Running the Gradle Spring Boot Project

This guide provides step-by-step instructions for building and running a Spring Boot project that uses Gradle as its
build tool.

---

## Steps to Build and Run

### 2. Build the Project

Use the Gradle wrapper to build the project. The Gradle wrapper (`./gradlew` on macOS/Linux or `gradlew.bat` on Windows)
ensures compatibility with the correct Gradle version.

Run the following command to build the project:

```bash
./gradlew build
```

This will compile the project, run all tests, and package the application into a JAR file.

### 3. Run the Application

After a successful build, you can run the application using:

```bash
./gradlew bootRun
```

Alternatively, if you want to execute the JAR file directly:

1. Locate the JAR file in the `build/libs` directory (e.g., `build/libs/<your-project-name>.jar`).
2. Run it with:
   ```bash
   java -jar build/libs/<your-project-name>.jar
   ```

---

## Common Gradle Commands

Here are some additional Gradle tasks you might find useful:

- **Clean the Project**: Removes all build artifacts:
  ```bash
  ./gradlew clean
  ```
- **Run Tests**: Executes all tests in the project:
  ```bash
  ./gradlew test
  ```
- **Generate a Dependency Report**:
  ```bash
  ./gradlew dependencies
  ```

---

## Application Configuration

The project uses the default `application.properties` or `application.yml` file located in the `src/main/resources`
directory for configuration.

You can override properties (e.g., port configuration or database settings) by specifying them as command-line
arguments. Example:

```bash
./gradlew bootRun --args='--server.port=8081'
```

---

## Additional Notes

- **Dependencies Management**: Dependencies are managed in the `build.gradle` file. Ensure proper updates by modifying
  the `dependencies` section.
- **Development Mode**: Use `bootRun` during development for faster restarts.


For more information, refer to the [Spring Boot documentation](https://spring.io/projects/spring-boot) or
the [Gradle documentation](https://docs.gradle.org/).

---

## Rest Tests are in [rest-test.http](rest-test.http)