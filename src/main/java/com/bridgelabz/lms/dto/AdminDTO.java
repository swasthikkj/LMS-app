package com.bridgelabz.lms.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class AdminDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String emailId;	
	private String profilePath;
	private boolean status;
	private String password;
	private LocalDateTime createStamp = LocalDateTime.now();
	private LocalDateTime updatedStamp;
}
