package com.bridgelabz.lms.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.HiringCandidateDTO;
import com.bridgelabz.lms.exception.CustomNotFoundException;
import com.bridgelabz.lms.model.AdminModel;
import com.bridgelabz.lms.model.BankDetailsModel;
import com.bridgelabz.lms.model.HiringCandidateModel;
import com.bridgelabz.lms.repository.AdminRepository;
import com.bridgelabz.lms.repository.BankDetailsRepository;
import com.bridgelabz.lms.repository.HiringCandidateRepository;
import com.bridgelabz.lms.util.TokenUtil;

@Service
public class CandidateHiringService implements ICandidateHiringService {
	@Autowired
	HiringCandidateRepository hireCandidateRepository;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	BankDetailsRepository bankDetailsRepository;

	@Override
	public HiringCandidateModel addHireCandidate(HiringCandidateDTO hireCandidateDTO, Long bankId, String token) {
		Long hireCandidateId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isTokenPresent = adminRepository.findById(hireCandidateId);
		if(isTokenPresent.isPresent()) {
			Optional<BankDetailsModel> isBankAccountPresent = bankDetailsRepository.findById(bankId);
			HiringCandidateModel model = new HiringCandidateModel(hireCandidateDTO);
			if(isBankAccountPresent.isPresent()) {
				model.setBankDetails(isBankAccountPresent.get());
			}
			hireCandidateRepository.save(model);
			String body = "Hiring candidate added successfully with bank Id" + model.getId();
			String subject = "hiring candidate added Successfully";
			mailService.send(model.getEmail(), subject, body);
			return model;
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public HiringCandidateModel updateHireCandidate(HiringCandidateDTO hireCandidateDTO, Long id, String token) {
		Long hireCandidateId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isTokenPresent = adminRepository.findById(hireCandidateId);
		if(isTokenPresent.isPresent()) {
			Optional<HiringCandidateModel> isIdPresent = hireCandidateRepository.findById(id);
			if(isIdPresent.isPresent()) {
				isIdPresent.get().setCicId(hireCandidateDTO.getCicId());
				isIdPresent.get().setFullName(hireCandidateDTO.getFullName());
				isIdPresent.get().setEmail(hireCandidateDTO.getEmail());
				isIdPresent.get().setMobileNum(hireCandidateDTO.getMobileNum());
				isIdPresent.get().setHiredDate(hireCandidateDTO.getHiredDate());
				isIdPresent.get().setAggrPer(hireCandidateDTO.getAggrPer());
				isIdPresent.get().setCity(hireCandidateDTO.getCity());
				isIdPresent.get().setState(hireCandidateDTO.getState());
				isIdPresent.get().setJobLocation(hireCandidateDTO.getJobLocation());
				isIdPresent.get().setPassedOutYear(hireCandidateDTO.getPassedOutYear());
				isIdPresent.get().setCreatorUser(hireCandidateDTO.getCreatorUser());
				isIdPresent.get().setCandidateStatus(hireCandidateDTO.getCandidateStatus());
				isIdPresent.get().setUpdatedTimeStamp(LocalDateTime.now());
				hireCandidateRepository.save(isIdPresent.get());
				String body = "Hiring candidate updated successfully with bank Id" + isIdPresent.get().getId();
				String subject = "hiring candidate updated Successfully";
				mailService.send(isIdPresent.get().getEmail(), subject, body);
				return isIdPresent.get();
			}
			throw new CustomNotFoundException(400, "Hiring candidate details not present");
		}
		throw new CustomNotFoundException(400, "Token Invalid");
	}

	@Override
	public Optional<HiringCandidateModel> getHireCandidateById(Long id, String token) {
		return hireCandidateRepository.findById(id);		
	}

	@Override
	public List<HiringCandidateModel> getAllHireCandidates(String token) {
		Long hireCandidateId = tokenUtil.decodeToken(token);
		List<HiringCandidateModel> getAllHireCandidates = hireCandidateRepository.findAll();
		if(getAllHireCandidates.size() > 0) {
			return getAllHireCandidates;
		} else {
			throw new CustomNotFoundException(400, "Admin not present");
		}	
	}

	@Override
	public HiringCandidateModel deleteHireCandidate(Long id, String token) {
		Long hireCandidateId = tokenUtil.decodeToken(token);
		Optional<HiringCandidateModel> isHireCandidatePresent = hireCandidateRepository.findById(id);
		if(isHireCandidatePresent.isPresent()) {
			hireCandidateRepository.delete(isHireCandidatePresent.get());
			String body = "Hiring candidate updated successfully with bank Id" + isHireCandidatePresent.get().getId();
			String subject = "hiring candidate updated Successfully";
			mailService.send(isHireCandidatePresent.get().getEmail(), subject, body);
			return isHireCandidatePresent.get();
		}
		throw new CustomNotFoundException(400, "Candidate not found");	
	}
}
