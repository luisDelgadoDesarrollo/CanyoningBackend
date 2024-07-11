FROM amazoncorretto:21
COPY /boot/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]