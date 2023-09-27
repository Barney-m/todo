FROM gradle:jdk17-jammy


FROM eclipse-temurin:17-jdk-jammy

WORKDIR /todo
CMD ["./gradlew", "clean", "bootJar"]

ARG JAR_FILE=build/libs/todo.jar
ADD ${JAR_FILE} /app.jar

EXPOSE 8791
ENTRYPOINT ["java","-jar","/app.jar"]