package edu.tushar.securitytrackingsystem.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.tushar.securitytrackingsystem.exception.StaffNotFoundException;
import edu.tushar.securitytrackingsystem.response.ResponseStructure;

@RestControllerAdvice
public class ApplicationException {

    @ExceptionHandler(StaffNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>>
    catchStaffNotFoundException(StaffNotFoundException exception) {

        ResponseStructure<String> response =
                new ResponseStructure<>();

        response.setStatus("ERROR");
        response.setMessage(exception.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseStructure<Map<String, String>>>
    handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
          .getFieldErrors()
          .forEach(error ->
                  errors.put(
                          error.getField(),
                          error.getDefaultMessage()));

        ResponseStructure<Map<String, String>> response =
                new ResponseStructure<>();

        response.setStatus("ERROR");
        response.setMessage("Validation Failed");
        response.setErrors(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                
    }
}