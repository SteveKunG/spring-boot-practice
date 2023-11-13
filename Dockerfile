FROM openjdk:17
EXPOSE 8080
ADD build/libs/spring-boot-docker-0.0.1.jar spring-boot-docker-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-docker-0.0.1.jar"]