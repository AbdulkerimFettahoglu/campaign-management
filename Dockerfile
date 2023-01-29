# docker build --tag=campaign-management:latest .
# docker run -p8887:8080 --name campaign-container campaign-management:latest 
FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]