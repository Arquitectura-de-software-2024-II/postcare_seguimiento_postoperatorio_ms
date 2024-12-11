# Usar OpenJDK 17
FROM openjdk:17-slim as builder

# Instala Maven
RUN apt-get update && apt-get install -y maven

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml .

# Copia el código fuente al contenedor
COPY src ./src

# Compila el proyecto y empaqueta el archivo JAR
RUN mvn clean package -DskipTests

# Usa una imagen con OpenJDK 22 para ejecutar la aplicación
FROM openjdk:22-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR desde la etapa de construcción
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto en el que la aplicación escuchará
EXPOSE 8081

# Ejecuta la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
