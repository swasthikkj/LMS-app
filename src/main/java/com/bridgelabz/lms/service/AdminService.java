package com.bridgelabz.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.AdminDTO;
import com.bridgelabz.lms.exception.CustomNotFoundException;
import com.bridgelabz.lms.model.AdminModel;
import com.bridgelabz.lms.repository.AdminRepository;
import com.bridgelabz.lms.util.Response;
import com.bridgelabz.lms.util.TokenUtil;

@Service
public class AdminService implements IAdminService {
	@Autowired
	AdminRepository adminRepository;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	MailService mailService;

	@Override
	public AdminModel addAdmin(AdminDTO adminDTO) {
		AdminModel model = new AdminModel(adminDTO);
		adminRepository.save(model);
		String body="Admin is added succesfully with adminId " + model.getId();
		String subject="Admin Registration Succesfull";
		mailService.send(model.getEmailId(), subject, body);		
		return model;
	}

	@Override
	public AdminModel updateAdmin(AdminDTO adminDTO, Long id, String token) {
		Long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepository.findById(id);
		if(isAdminPresent.isPresent()) {
			isAdminPresent.get().setFirstName(adminDTO.getFirstName());
			isAdminPresent.get().setLastName(adminDTO.getLastName());
			isAdminPresent.get().setMobileNumber(adminDTO.getMobileNumber());
			isAdminPresent.get().setEmailId(adminDTO.getEmailId());
			isAdminPresent.get().setPassword(adminDTO.getPassword());
			isAdminPresent.get().setCreateStamp(adminDTO.getCreateStamp().now());
			isAdminPresent.get().setProfilePath(adminDTO.getProfilePath());
			isAdminPresent.get().setStatus(adminDTO.isStatus());
			isAdminPresent.get().setUpdatedStamp(adminDTO.getUpdatedStamp());
			adminRepository.save(isAdminPresent.get());
			return isAdminPresent.get();
		}
		throw new CustomNotFoundException(400,"not present");
	}

	@Override
	public Optional<AdminModel> getAdminById(Long id, String token) {
		return adminRepository.findById(id);
	}
	
	@Override
	public List<AdminModel> getAllAdmins(String token) {
		Long adminId = tokenUtil.decodeToken(token);
		List<AdminModel> getAllAdmins = adminRepository.findAll();
		if(getAllAdmins.size()>0) {
			return getAllAdmins;
		} else {
			throw new CustomNotFoundException(400, "Admin not present");
		}	
	}

	@Override
	public AdminModel deleteAdmin(Long id, String token) {
		Long adminId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isAdminPresent = adminRepository.findById(id);
		if(isAdminPresent.isPresent()) {
			adminRepository.delete(isAdminPresent.get());
			return isAdminPresent.get();
		}
		throw new CustomNotFoundException(400, "Admin not found");
	}

	@Override
	public Response login(String emailId, String password) {
		Optional<AdminModel> isEmailPresent = adminRepository.findByEmailId(emailId);
		if(isEmailPresent.isPresent()) {
			if(isEmailPresent.get().getPassword().equals(password)){
				String token = tokenUtil.createToken(isEmailPresent.get().getId());
				return new Response(400, "login succesfull", token);
			}
			throw new CustomNotFoundException(400, "Invalid credentials");
		}
		throw new CustomNotFoundException(400, "User not found");
	}
	
	@Override
    public Response resetPassword(String emailId) {
        Optional<AdminModel> isMailPresent = adminRepository.findByEmailId(emailId);
        if (isMailPresent.isPresent()){
            String token = tokenUtil.createToken(isMailPresent.get().getId());
            return new Response(200,"Reset password",token);

        }
        throw new CustomNotFoundException(400, "Email not found");
    }

    @Override
    public AdminModel changePassword(String token, String password) {
        Long decode = tokenUtil.decodeToken(token);
        Optional<AdminModel> isTokenPresent = adminRepository.findById(decode);
        if (isTokenPresent.isPresent()) {
            isTokenPresent.get().setPassword(password);
            adminRepository.save(isTokenPresent.get());
            return isTokenPresent.get();
        }
        throw new CustomNotFoundException(400, "Token not found");
    }
}
