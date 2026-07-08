package edu.tushar.securitytrackingsystem.util;

import org.springframework.stereotype.Component;

@Component
public class StaffCodeGenerator {
	
	public String generateStaffCode(int year, int month, int sequence) {
		return String.format("SEC%02d%02d%02d", year, month, sequence);
	}
}
  