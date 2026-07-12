package edu.tushar.securitytrackingsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StaffStatusRequestDto {
	
	@NotNull(message = "Active Status is required")
	private Boolean active;
}
