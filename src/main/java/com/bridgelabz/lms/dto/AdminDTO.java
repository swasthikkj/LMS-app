package com.bridgelabz.lms.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
@Data
public class AdminDTO {
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "First name Invalid")
	private String firstName;
	@Pattern(regexp = "^[a-z]{1}[a-zA-Z\\s]{2,}$", message = "last name Invalid")
	private String lastName;
	@Pattern(regexp = "^[9,8,7,6]{1}[0-9]{9}$", message = "mobile number invalid")
	private String mobileNumber;
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "email is invalid")
	private String emailId;
	@NotBlank(message = "profilepath cannot be empty")
	private String profilePath;
	private boolean status;
	@Pattern(regexp = "^[a-zA-Z0-9*&@]{8,20}$", message = "password is invalid")
	private String password;
	private LocalDateTime createStamp;
	private LocalDateTime updatedStamp;
}
