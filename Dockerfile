FROM openjdk:8-jdk-alpine
ADD  target/empdetails.jar  empdetails.jar 
EXPOSE 8085
ENTRYPOINT ["java","-jar","empdetails.jar"] 
 
