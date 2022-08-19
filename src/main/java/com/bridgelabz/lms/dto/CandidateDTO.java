package com.bridgelabz.lms.dto;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
public class CandidateDTO {
	   @GenericGenerator(name = "learner_details", strategy = "increment")
	   @GeneratedValue(generator = "learner_details")
	   private Long id;
	   private String cicId;
	   private String fullName;
	   private String email;
	   private String mobileNum;
	   private String hiredDate;
	   private String degree;
	   private Double aggrPer;
	   private String city;
	   private String state;
	   private String preferredJobLocation;
	   private String status;
	   private String passedOutYear;
	   private String creatorUser;
	   private String candidateStatus;

	   @CreationTimestamp
	   private LocalDateTime createdTimeStamp;

	   @UpdateTimestamp
	   private LocalDateTime updatedTimeStamp;
}
