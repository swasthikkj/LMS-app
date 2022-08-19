package com.bridgelabz.lms.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.lms.dto.AdminDTO;
import com.bridgelabz.lms.model.AdminModel;
import com.bridgelabz.lms.util.Response;

public interface IAdminService {
	AdminModel addAdmin(AdminDTO adminDTO);

	AdminModel updateAdmin(AdminDTO adminDTO, Long id, String token);
	
	AdminModel deleteAdmin(Long id,  String token);

	Response login(String emailId, String password);

	Optional<AdminModel> getAdminById(Long id, String token);

	List<AdminModel> getAllAdmins(String token);

	AdminModel changePassword(String token, String password);

	Response resetPassword(String emailId);
	
}
