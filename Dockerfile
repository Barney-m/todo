FROM eclipse-temurin:17-jdk-jammy

VOLUME /tmp

CMD ["./gradlew", "clean", "bootJar"]

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /app/app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]