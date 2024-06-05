#Configuración Dockerfile para compilación en Jenkins local
#FROM amazoncorretto:17.0.9-alpine
#ADD target/spring_boot_web_mvc_jdbc_ventas.jar spring_boot_web_mvc_jdbc_ventas.jar
#ENTRYPOINT ["java", "-jar","spring_boot_web_mvc_jdbc_ventas.jar"]


#Configuración Dockerfile para compilación y despliegue en Render
FROM maven:3-amazoncorretto-21 AS build
COPY . .
RUN mvn clean package -DskipTests


FROM amazoncorretto:21-alpine
COPY --from=build /target/wu-backend.jar wu-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","wu-backend.jar"]
