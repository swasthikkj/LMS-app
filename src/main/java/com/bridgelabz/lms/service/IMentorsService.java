package com.bridgelabz.lms.service;


import java.util.List;
import java.util.Optional;

import com.bridgelabz.lms.dto.MentorsDTO;

import com.bridgelabz.lms.model.MentorsModel;

public interface IMentorsService {
	MentorsModel addMentor(MentorsDTO mentorsDTO, String token);
	
	MentorsModel updateMentor(MentorsDTO mentorsDTO, Long id, String token);

	Optional<MentorsModel> getMentorById(Long id, String token);

	List<MentorsModel> getAllMentors(String token);

	MentorsModel deleteMentor(Long id, String token);

	List<MentorsModel> getMentorsByRole(String role, String token);

	long mentorsRoleCount(String mentorRole, String token);
	
}
