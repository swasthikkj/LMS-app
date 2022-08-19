package com.bridgelabz.lms.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class AdminNotFoundException extends RuntimeException {
	private int statuscode;
	private String message;

	public AdminNotFoundException(int statuscode, String message) {
		super(message);
		this.statuscode = statuscode;
		this.message = message;
	}
}
