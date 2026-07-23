FROM openjdk:8
EXPOSE 8080
ADD target/grocery-store.jar grocery-store.jar
ENTRYPOINT ["java", "-jar", "/grocery-store.jar"]
