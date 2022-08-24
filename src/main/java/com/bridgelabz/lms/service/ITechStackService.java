package com.bridgelabz.lms.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.lms.dto.TechStackDTO;
import com.bridgelabz.lms.model.TechStackModel;

public interface ITechStackService {
	TechStackModel addTechStack(TechStackDTO techStackDTO, String token, List<Long> adminId);
	
	TechStackModel updateTechStack(TechStackDTO techStackDTO,Long id, String token);
	
	Optional<TechStackModel> getTechById(Long id, String token);

	List<TechStackModel> getAllTechStacks(String token);

	TechStackModel deleteTechStack(Long id, String token);

}
