package edu.tushar.securitytrackingsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StaffRequestDto {
	@NotBlank(message="Name is required")
	private String name;
	
	@Pattern(regexp = "^[0-9]{10}$",
			message = "Mobile must be 10 digits")
	private String mobile;
	
	@Email(message = "Invalid email")
	private String email;
	
	@Min(value = 18, message = "Age must be at least 18")
	@Max(value = 60, message = "Age cannot exceed 60")
	private int age;
	
	@NotBlank(message = "Address is required")
	private String address; 
	
	@NotBlank(message="Designation is required")
	private String designation;
}
