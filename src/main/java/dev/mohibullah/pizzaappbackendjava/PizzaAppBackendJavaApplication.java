package dev.mohibullah.pizzaappbackendjava;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaAppBackendJavaApplication {

	@Value("${server.port}")
	private int serverPort;

	@Value("${spring.datasource.url}")
	private String databaseUrl;

	public static void main(String[] args) {
		SpringApplication.run(PizzaAppBackendJavaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("\n\nServer started on http://localhost:" + serverPort);
			System.out.println("Database running on " + databaseUrl + "\n\n");
		};
	}


}
