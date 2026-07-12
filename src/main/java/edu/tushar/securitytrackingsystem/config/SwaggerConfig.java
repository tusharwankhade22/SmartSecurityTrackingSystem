package edu.tushar.securitytrackingsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI securityTrackingOpenAPI() {

        Contact contact = new Contact()
                .name("Tushar Vijay Wankhade")
                .email("tusharwankhade388@gmail.com")
                .url("https://github.com/tusharwankhade22");

        License license = new License()
                .name("Apache License 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info()
                .title("Smart Security Tracking System API")
                .version("1.0.0")
                .description("""
                        ## Smart Security Tracking System

                        The Smart Security Tracking System is a RESTful Spring Boot application
                        developed to manage security personnel, QR Code based attendance,
                        user management and attendance tracking.

                        ### 👨‍💻 Developed By
                        **Tushar Vijay Wankhade**

                        📧 Email : tusharwankhade388@gmail.com

                        📱 Phone : +91 8080475318

                        ---

                        ## 🚀 Technology Stack

                        - Java 21
                        - Spring Boot 3
                        - Spring Security (JWT)
                        - Spring Data JPA
                        - Hibernate
                        - MySQL
                        - Maven
                        - Swagger / OpenAPI 3
                        - Lombok
                        - QR Code Generator (ZXing)

                        ---

                        ## 📌 Project Modules

                        ### Staff Management
                        - Add Staff
                        - Get All Staff
                        - Get Staff By Id
                        - Update Staff
                        - Update Staff Status
                        - Generate QR Code

                        ### Attendance Management
                        - QR Check-In
                        - QR Check-Out
                        - Attendance Logs
                        - Attendance Reports

                        ### User Management
                        - User Registration
                        - Login
                        - Role Based Authorization
                        - JWT Authentication

                        ---

                        ## ⭐ Key Features

                        - QR Code Based Attendance
                        - Staff Management
                        - Attendance Tracking
                        - User Management
                        - Standard API Response
                        - Global Exception Handling
                        - Request Validation
                        - RESTful API Design
                        - Clean Architecture

                        ---
                        """)
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/tusharwankhade22/SmartSecurityTrackingSystem"));
    }
}
