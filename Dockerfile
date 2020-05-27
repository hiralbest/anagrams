FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} anagram.jar
ENTRYPOINT ["java","-jar","/anagram.jar"]
