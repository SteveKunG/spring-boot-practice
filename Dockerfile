FROM openjdk:17
COPY build/libs/*.jar spring-boot-docker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-docker.jar"]