#Configuración Dockerfile para compilación y despliegue en Render
FROM maven:3-amazoncorretto-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine
COPY --from=build /target/jfmoda168.jar jfmoda168.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","jfmoda168.jar"]
