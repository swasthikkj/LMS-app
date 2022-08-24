package com.bridgelabz.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.bridgelabz.lms.dto.HiringCandidateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hiring_candidate")
@Data
@NoArgsConstructor
public class HiringCandidateModel {
	@Id
	@GenericGenerator(name = "hiring_candidate", strategy = "increment")
	@GeneratedValue(generator = "hiring_candidate")
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
	private String jobLocation;
	private String status;
	private String passedOutYear;
	private String creatorUser;
	private String candidateStatus;

	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime creationTimeStamp;

	@JsonIgnore
	@UpdateTimestamp
	private LocalDateTime updatedTimeStamp;

	@OneToOne
	private BankDetailsModel bankDetails;

	public HiringCandidateModel(HiringCandidateDTO hireCandidateDTO) {
		this.cicId = hireCandidateDTO.getCicId();
		this.fullName = hireCandidateDTO.getFullName();
		this.email = hireCandidateDTO.getEmail();
		this.mobileNum = hireCandidateDTO.getMobileNum();
		this.hiredDate = hireCandidateDTO.getHiredDate();
		this.degree = hireCandidateDTO.getDegree();
		this.aggrPer = hireCandidateDTO.getAggrPer();
		this.city = hireCandidateDTO.getCity();
		this.state = hireCandidateDTO.getState();
		this.jobLocation = hireCandidateDTO.getJobLocation();
		this.status = hireCandidateDTO.getStatus();
		this.passedOutYear = hireCandidateDTO.getPassedOutYear();
		this.creatorUser = hireCandidateDTO.getCreatorUser();
		this.candidateStatus = hireCandidateDTO.getCandidateStatus();
	}
}
