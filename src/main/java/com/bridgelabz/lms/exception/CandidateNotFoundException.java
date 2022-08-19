package com.bridgelabz.lms.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CandidateNotFoundException extends RuntimeException {
	private int statuscode;
	private String message;

	public CandidateNotFoundException(int statuscode, String message) {
		super(message);
		this.statuscode = statuscode;
		this.message = message;
	}
}
