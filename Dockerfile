FROM openjdk:17

WORKDIR /app

COPY target/ShoppingCartService-0.0.1-SNAPSHOT.jar /app/ShoppingCartService.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "ShoppingCartService.jar"]