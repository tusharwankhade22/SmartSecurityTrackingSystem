package edu.tushar.securitytrackingsystem.response;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private String status;
	private String message;
	private T data;
	private Object errors;
}
