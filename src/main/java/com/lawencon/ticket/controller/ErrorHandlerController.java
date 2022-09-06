package com.lawencon.ticket.controller;

import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lawencon.ticket.dto.ErrorRes;
import com.lawencon.ticket.exception.InvalidLoginException;

@ControllerAdvice
public class ErrorHandlerController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleDtoValidation(MethodArgumentNotValidException e) {
		List<String> message = new ArrayList<>();
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			message.add(fieldError.getDefaultMessage());
		}
		ErrorRes<List<String>> errorRes = new ErrorRes<>();
		errorRes.setMessage(message);

		return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<?> handleFailLogin(InvalidLoginException e) {
		ErrorRes<String> response = new ErrorRes<>();
		response.setMessage(NestedExceptionUtils.getMostSpecificCause(e).getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(PSQLException.class)
	public ResponseEntity<?> handleDuplicate(PSQLException e){
		ErrorRes<String> response = new ErrorRes<>();
		response.setMessage(NestedExceptionUtils.getMostSpecificCause(e).getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
