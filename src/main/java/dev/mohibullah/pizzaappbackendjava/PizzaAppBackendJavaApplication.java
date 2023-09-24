package dev.mohibullah.pizzaappbackendjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaAppBackendJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaAppBackendJavaApplication.class, args);
		System.out.println("\n\nServer started on http://localhost:3000" );
		System.out.println("DataBase running on jdbc:postgresql://localhost:5432/pizza_app_java\n\n" );
	}

}
