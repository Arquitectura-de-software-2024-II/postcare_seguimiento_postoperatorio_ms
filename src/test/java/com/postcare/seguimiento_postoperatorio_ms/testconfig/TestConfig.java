package com.postcare.seguimiento_postoperatorio_ms.testconfig;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

@Configuration
@TestPropertySource(locations = "classpath:application-test.properties")  // Opcional si tienes propiedades específicas para test
public class TestConfig {

    static {
        // Cargar el archivo .env
        Dotenv dotenv = Dotenv.load();

        String mongoUri = dotenv.get("MONGO_URI");
        String mongoDatabase = dotenv.get("MONGO_DATABASE");
        String serverPort = dotenv.get("SERVER_PORT");
        String appName = dotenv.get("SPRING_APPLICATION_NAME");

        // Imprimir para verificar
        System.out.println("MONGO_URI: " + mongoUri);
        System.out.println("MONGO_DATABASE: " + mongoDatabase);
        System.out.println("SERVER_PORT: " + serverPort);
        System.out.println("SPRING_APPLICATION_NAME: " + appName);

        // Establecer las propiedades de sistema
        System.setProperty("MONGO_URI", mongoUri);
        System.setProperty("MONGO_DATABASE", mongoDatabase);
        System.setProperty("SERVER_PORT", serverPort);
        System.setProperty("SPRING_APPLICATION_NAME", appName);
        // Establecer las propiedades de entorno necesarias para los tests
    }
}
