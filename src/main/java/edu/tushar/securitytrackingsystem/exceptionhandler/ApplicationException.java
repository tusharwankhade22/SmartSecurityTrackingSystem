package edu.tushar.securitytrackingsystem.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.tushar.securitytrackingsystem.exception.StaffNotFoundException;

@RestControllerAdvice
public class ApplicationException {

	@ExceptionHandler(StaffNotFoundException.class)
	public ResponseEntity<String> catchStaffNotFoundException(StaffNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
