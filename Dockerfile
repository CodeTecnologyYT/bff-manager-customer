FROM --platform=linux/amd64 eclipse-temurin:17-jdk
WORKDIR /app
COPY build/libs/bff-manager-customer.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]