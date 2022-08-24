package com.bridgelabz.lms.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.lms.dto.HiringCandidateDTO;
import com.bridgelabz.lms.model.HiringCandidateModel;

public interface ICandidateHiringService {
	HiringCandidateModel addHireCandidate(HiringCandidateDTO hireCandidateDTO, Long bankId, String token);

	HiringCandidateModel updateHireCandidate(HiringCandidateDTO hireCandidateDTO, Long id, String token);

	Optional<HiringCandidateModel> getHireCandidateById(Long id, String token);

	List<HiringCandidateModel> getAllHireCandidates(String token);

	HiringCandidateModel deleteHireCandidate(Long id, String token);
}
