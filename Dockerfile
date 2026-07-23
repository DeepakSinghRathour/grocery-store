FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/grocery-store.jar grocery-store.jar
ENTRYPOINT ["java", "-jar", "grocery-store.jar"]
