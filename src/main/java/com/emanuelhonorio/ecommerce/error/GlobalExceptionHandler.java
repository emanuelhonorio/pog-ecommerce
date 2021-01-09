package com.emanuelhonorio.ecommerce.error;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.emanuelhonorio.ecommerce.error.exceptions.ResourceAlreadyExistsException;
import com.emanuelhonorio.ecommerce.error.exceptions.ResourceNotFoundException;
import com.emanuelhonorio.ecommerce.error.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorResponse err = new ErrorResponse(404, ex.getMessage());
		return ResponseEntity.ok().body(err);
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<?> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
		ErrorResponse err = new ErrorResponse(409, ex.getMessage());
		return ResponseEntity.ok().body(err);
	}
	
	@ExceptionHandler({
		ConstraintViolationException.class,
		DataIntegrityViolationException.class,
	})
	public ResponseEntity<?> handleMySQLIntegrityConstraintViolationException(Exception ex) {
		ErrorResponse err = new ErrorResponse(409, ex.getMessage());
		return ResponseEntity.ok().body(err);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorResponse err = new ErrorResponse(409, "One or more fields are not valid");
		err.setFieldErrors(getFielErrors(ex.getBindingResult()));
		
		return handleExceptionInternal(ex, err, headers, HttpStatus.BAD_REQUEST, request);
	}

	private Map<String, String> getFielErrors(BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		result.getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}

	
}
