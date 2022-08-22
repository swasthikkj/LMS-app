package com.bridgelabz.lms.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.CandidateDTO;
import com.bridgelabz.lms.exception.CandidateNotFoundException;
import com.bridgelabz.lms.model.AdminModel;
import com.bridgelabz.lms.model.CandidateModel;
import com.bridgelabz.lms.repository.AdminRepository;
import com.bridgelabz.lms.repository.CandidateRepository;
import com.bridgelabz.lms.util.TokenUtil;

@Service
public class CandidateService implements ICandidateService {
	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	AdminRepository adminRepository;

	@Override
	public CandidateModel addCandidate(CandidateDTO candidateDTO, String token) {
		Long candidateId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isTokenPresent = adminRepository.findById(candidateId);
		if(isTokenPresent.isPresent()) {
			CandidateModel model = new CandidateModel(candidateDTO);
			candidateRepository.save(model);
			return model;
		}
		throw new CandidateNotFoundException(400, "Token not found");
	}

	@Override
	public CandidateModel updateCandidate(CandidateDTO candidateDTO, Long id, String token) {
		Long candidateId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isTokenPresent = adminRepository.findById(candidateId);
		if(isTokenPresent.isPresent()) {
			Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(id);
			if(isCandidatePresent.isPresent()) {
				isCandidatePresent.get().setCicId(candidateDTO.getCicId());
				isCandidatePresent.get().setFullName(candidateDTO.getFullName());
				isCandidatePresent.get().setEmail(candidateDTO.getEmail());
				isCandidatePresent.get().setMobileNum(candidateDTO.getMobileNum());
				isCandidatePresent.get().setHiredDate(candidateDTO.getHiredDate());
				isCandidatePresent.get().setDegree(candidateDTO.getDegree());
				isCandidatePresent.get().setAggrPer(candidateDTO.getAggrPer());
				isCandidatePresent.get().setCity(candidateDTO.getCity());
				isCandidatePresent.get().setState(candidateDTO.getState());
				isCandidatePresent.get().setPreferredJobLocation(candidateDTO.getPreferredJobLocation());
				isCandidatePresent.get().setStatus(candidateDTO.getStatus());
				isCandidatePresent.get().setPassedOutYear(candidateDTO.getPassedOutYear());
				isCandidatePresent.get().setCreatorUser(candidateDTO.getCreatorUser());
				isCandidatePresent.get().setCandidateStatus(candidateDTO.getCandidateStatus());
				isCandidatePresent.get().setCreatedTimeStamp(LocalDateTime.now());
				isCandidatePresent.get().setUpdatedTimeStamp(LocalDateTime.now());
				candidateRepository.save(isCandidatePresent.get());
				return isCandidatePresent.get();
			}
			throw new CandidateNotFoundException(400, "not present");
		}
		throw new CandidateNotFoundException(400, "Token not found");
	}

	@Override
	public Optional<CandidateModel> getCandidateById(Long id, String token) {
		return candidateRepository.findById(id);
	}

	@Override
	public List<CandidateModel> getAllCandidates(String token) {
		Long candidateId = tokenUtil.decodeToken(token);
		List<CandidateModel> getAllCandidates = candidateRepository.findAll();
		if(getAllCandidates.size()>0) {
			return getAllCandidates;
		} else {
			throw new CandidateNotFoundException(400, "Admin not present");
		}	
	}

	@Override
	public CandidateModel deleteCandidate(Long id, String token) {
		Long candidateId = tokenUtil.decodeToken(token);
		Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(id);
		if(isCandidatePresent.isPresent()) {
			candidateRepository.delete(isCandidatePresent.get());
			return isCandidatePresent.get();
		}
		throw new CandidateNotFoundException(400, "Admin not found");
	}

	@Override
	public List<CandidateModel> getCandidateByStatus(String status, String token) {
		List<CandidateModel> isStatusPresent = candidateRepository.findByStatus(status);
		if (isStatusPresent.size() > 0) {
			return isStatusPresent;
		}
		throw new CandidateNotFoundException(400, "Admin not found");
	}

	@Override
	public CandidateModel changeStatus(Long id, String status, String token) {
		Optional<CandidateModel> isIdPresent = candidateRepository.findById(id);
		if (isIdPresent.isPresent()) {
			isIdPresent.get().setStatus(status);
			candidateRepository.save(isIdPresent.get());
			return isIdPresent.get();
		}
		throw new CandidateNotFoundException(400, "Status not found");
	}
	
	@Override
	public long statusCount(String status, String token) {
		List<CandidateModel> isStatusPresent = candidateRepository.findByStatus(status);
		if(isStatusPresent.size() > 0) {
			return isStatusPresent.stream().count();
		}
		throw new CandidateNotFoundException(400, "status not found");
	}
	
}
