package com.bridgelabz.lms.exception.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.lms.exception.AdminNotFoundException;
import com.bridgelabz.lms.util.Response;
@ControllerAdvice
public class AdminExceptionHandler {
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<Response> handleId(AdminNotFoundException ab) {
		Response response = new Response();
		response.setErrorcode(400);
		response.setMessage(ab.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
