package com.bridgelabz.lms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.TechStackDTO;
import com.bridgelabz.lms.exception.CustomNotFoundException;
import com.bridgelabz.lms.model.AdminModel;
import com.bridgelabz.lms.model.TechStackModel;
import com.bridgelabz.lms.repository.AdminRepository;
import com.bridgelabz.lms.repository.TechStackRepository;
import com.bridgelabz.lms.util.TokenUtil;

@Service
public class TechStackService implements ITechStackService {
	@Autowired
	TechStackRepository techRepository;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	AdminRepository adminRepository;

	@Override
	public TechStackModel addTechStack(TechStackDTO techStackDTO, String token, List<Long> adminId) {
		Long techId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isTokenPresent = adminRepository.findById(techId);
		if(isTokenPresent.isPresent()) {
			List<AdminModel> list = new ArrayList<>();
			adminId.stream().forEach(id -> {
				Optional<AdminModel> isIdPresent = adminRepository.findById(id);
				if (isIdPresent.isPresent()) {
					list.add(isIdPresent.get());
				}
			});
			TechStackModel model = new TechStackModel(techStackDTO);
			if(list.size() > 0) {
				model.setCreatorUser(list);
			}
			techRepository.save(model);
			return model;
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public TechStackModel updateTechStack(TechStackDTO techStackDTO, Long id, String token) {
		Long techId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isTokenPresent = adminRepository.findById(techId);
		if(isTokenPresent.isPresent()) {
			Optional<TechStackModel> isTechPresent = techRepository.findById(id);
			if(isTechPresent.isPresent()) {
				isTechPresent.get().setTechName(techStackDTO.getTechName());
				isTechPresent.get().setImagePath(techStackDTO.getImagePath());
				isTechPresent.get().setStatus(techStackDTO.isStatus());
				techRepository.save(isTechPresent.get());
				return isTechPresent.get();
			}
			throw new CustomNotFoundException(400,"tech stack not present");
		}
		throw new CustomNotFoundException(400,"token invalid");
	}

	@Override
	public Optional<TechStackModel> getTechById(Long id, String token) {
		return techRepository.findById(id);
	}

	@Override
	public List<TechStackModel> getAllTechStacks(String token) {
		Long techId = tokenUtil.decodeToken(token);
		List<TechStackModel> getAllTechStacks = techRepository.findAll();
		if(getAllTechStacks.size()>0) {
			return getAllTechStacks;
		} else {
			throw new CustomNotFoundException(400, "Tech stack not present");
		}
	}

	@Override
	public TechStackModel deleteTechStack(Long id, String token) {
		Long techId = tokenUtil.decodeToken(token);
		Optional<TechStackModel> isTechPresent = techRepository.findById(id);
		if(isTechPresent.isPresent()) {
			techRepository.delete(isTechPresent.get());
			return isTechPresent.get();
		}
		throw new CustomNotFoundException(400, "Tech stack not found");
	}
}
