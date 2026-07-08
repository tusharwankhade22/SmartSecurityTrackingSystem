package edu.tushar.securitytrackingsystem.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StaffRequestDto {
	@NotBlank(message="Fisrt Name is required") 
	@Size(max = 50, message = "First name cannot exceed 50 characters")
	private String firstName;
	
	@Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;
	
	@Pattern(regexp = "^[0-9]{10}$",
			message = "Phone number must be 10 digits")
	private String phoneNumber;
	
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;
	
	@Size(max = 500, message = "Address cannot exceed 500 characters")
    private String address;
	
	@NotBlank(message = "Designation is required")
    @Size(max = 50, message = "Designation cannot exceed 50 characters")
    private String designation;
	
	private String gender;
	
	@Past(message = "Date of birth must be in the past")
	private LocalDate dateOfBirth;
	
	@Pattern(regexp = "^[6-9]\\d{9}$",message = "Emergency contact must be a valid 10-digit")
    private String emergencyContact;
	
	private LocalDate joiningDate;
}
