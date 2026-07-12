package edu.tushar.securitytrackingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SecuritytrackingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritytrackingsystemApplication.class, args);
	}
}
