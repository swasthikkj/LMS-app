package com.bridgelabz.lms.util;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Component
@Data
@ToString
public class Email {
	String to;
	String from;
	String subject;
	String body;
}
