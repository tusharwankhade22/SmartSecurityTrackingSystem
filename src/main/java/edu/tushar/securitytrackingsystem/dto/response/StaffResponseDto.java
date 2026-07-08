package edu.tushar.securitytrackingsystem.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StaffResponseDto {

	 private Long id;

	    private String staffCode;

	    private String firstName;

	    private String lastName;

	    private String email;

	    private String phoneNumber;

	    private String designation;

	    private String gender;

	    private LocalDate joiningDate;

	    private Boolean active;

}