package edu.tushar.securitytrackingsystem.exception;

public class StaffNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Staff not found!!";
	}
}
