package edu.tushar.securitytrackingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.models.OpenAPI;

@SpringBootApplication
@EnableScheduling
public class SecuritytrackingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritytrackingsystemApplication.class, args);
	}
	
	@Bean
	public OpenAPI getOpenApi() {
		return new OpenAPI();
	}

}
