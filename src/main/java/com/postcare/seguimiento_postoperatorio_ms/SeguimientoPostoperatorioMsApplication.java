package com.postcare.seguimiento_postoperatorio_ms;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeguimientoPostoperatorioMsApplication {

	public static void main(String[] args) {
		// Cargar el archivo .env para cargar las variables de entorno
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

		SpringApplication.run(SeguimientoPostoperatorioMsApplication.class, args);
        System.out.println("Seguimiento Postoperaorio MS");
	}
}
