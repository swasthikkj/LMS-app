package com.bridgelabz.lms.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.lms.dto.CandidateDTO;
import com.bridgelabz.lms.model.CandidateModel;

public interface ICandidateService {

	CandidateModel addCandidate(CandidateDTO candidateDTO, String token, Long techId);

	CandidateModel updateCandidate(CandidateDTO candidateDTO, Long id, String token);

	Optional<CandidateModel> getCandidateById(Long id, String token);

	List<CandidateModel> getAllCandidates(String token);

	CandidateModel deleteCandidate(Long id, String token);

	List<CandidateModel> getCandidateByStatus(String status, String token);

	CandidateModel changeStatus(Long id, String status, String token);

	long statusCount(String status, String token);

}
