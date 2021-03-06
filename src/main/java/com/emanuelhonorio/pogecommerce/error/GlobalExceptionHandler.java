package com.emanuelhonorio.pogecommerce.error;

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

import com.emanuelhonorio.pogecommerce.error.exceptions.OutOfStockException;
import com.emanuelhonorio.pogecommerce.error.exceptions.ResourceAlreadyExistsException;
import com.emanuelhonorio.pogecommerce.error.exceptions.ResourceNotFoundException;
import com.emanuelhonorio.pogecommerce.error.exceptions.ResourceOwnerException;
import com.emanuelhonorio.pogecommerce.error.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler({ ConstraintViolationException.class, DataIntegrityViolationException.class,
			ResourceAlreadyExistsException.class, OutOfStockException.class })
	public ResponseEntity<?> handleDefaultConflictExceptions(Exception ex) {
		ErrorResponse err = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}

	@ExceptionHandler({ ResourceOwnerException.class })
	public ResponseEntity<?> handleDefaultForbiddenExceptions(Exception ex) {
		ErrorResponse err = new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
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
