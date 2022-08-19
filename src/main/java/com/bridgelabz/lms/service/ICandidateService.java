package com.bridgelabz.lms.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.lms.dto.CandidateDTO;
import com.bridgelabz.lms.model.CandidateModel;

public interface ICandidateService {

	CandidateModel addCandidate(CandidateDTO candidateDTO, String token);

	CandidateModel updateCandidate(CandidateDTO candidateDTO, Long id, String token);

	Optional<CandidateModel> getCandidateById(Long id, String token);

	List<CandidateModel> getAllCandidates(String token);

	CandidateModel deleteCandidate(Long id, String token);

	List<CandidateModel> getCandidateByStatus(String status);

	CandidateModel changeStatus(Long id, String status);

}
