package com.bridgelabz.lms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
@Data
public class TechStackDTO {
	@NotBlank(message = "image path cannot be empty")
	private String imagePath;
	@NotBlank(message = "status cannot be empty")
	private boolean status;
	@Pattern(regexp = "^[a-zA-Z\\s]{2,}$", message = "tech name is Invalid")
	private String techName;
}
