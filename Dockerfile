FROM gradle:8.3.0-jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/
RUN gradle build -x test --no-daemon 

FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8081
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/todo-app.jar

ENTRYPOINT ["java","-jar","/app/todo-app.jar"]