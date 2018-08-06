FROM openjdk:8-jdk-alpine
ADD  target/empdemo.war  empdemo.war
EXPOSE 8085
ENTRYPOINT ["java","-jar","empdemo.war"]

