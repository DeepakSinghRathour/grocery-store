FROM eclipse-temurin:17-jdk
EXPOSE 8080
ADD target/grocery-store.jar grocery-store.jar
ENTRYPOINT ["java", "-jar", "grocery-store.jar"]
