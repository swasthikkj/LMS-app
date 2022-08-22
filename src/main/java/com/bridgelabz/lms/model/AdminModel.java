package com.bridgelabz.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.lms.dto.AdminDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "Admin")
@Data
@NoArgsConstructor
public class AdminModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String emailId;	
	private String profilePath;
	private boolean status;
	private String password;
	private LocalDateTime createStamp;
	private LocalDateTime updatedStamp;
	
	public AdminModel(AdminDTO adminDTO) {
		this.firstName = adminDTO.getFirstName();
		this.lastName = adminDTO.getLastName();
		this.mobileNumber = adminDTO.getMobileNumber();
		this.emailId = adminDTO.getEmailId();
		this.profilePath = adminDTO.getProfilePath();
		this.status = adminDTO.isStatus();
		this.password = adminDTO.getPassword();
		this.createStamp = adminDTO.getCreateStamp().now();
		this.updatedStamp = adminDTO.getUpdatedStamp().now();
	}
}
