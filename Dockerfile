FROM openjdk:11
ADD target/*.jar go-api-notification
ENTRYPOINT ["java", "-jar","go-api-notification"]
EXPOSE 8086