package com.bridgelabz.lms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.BankDetailsDTO;
import com.bridgelabz.lms.exception.CustomNotFoundException;
import com.bridgelabz.lms.model.AdminModel;
import com.bridgelabz.lms.model.BankDetailsModel;
import com.bridgelabz.lms.repository.AdminRepository;
import com.bridgelabz.lms.repository.BankDetailsRepository;
import com.bridgelabz.lms.util.TokenUtil;

@Service
public class BankDetailsService implements IBankDetailsService {
	@Autowired
	BankDetailsRepository bankDetailsRepository;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	MailService mailService;

	@Override
	public BankDetailsModel addBankDetails(BankDetailsDTO bankDetailsDTO, Long adminId, String token) {
		Long bankAccountId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isTokenPresent = adminRepository.findById(bankAccountId);
		if (isTokenPresent.isPresent()) {
			Optional<AdminModel> isAdminIdPresent = adminRepository.findById(adminId);
			BankDetailsModel model = new BankDetailsModel(bankDetailsDTO);
			if (isAdminIdPresent.isPresent()) {
				model.setCreatorUser(isAdminIdPresent.get());
			}
			bankDetailsRepository.save(model);
			String body = "BankDetails is added succesfully with id " + model.getId();
			String subject = "BankDetails added Succesfully";
			mailService.send(model.getEmail(), subject, body);
			return model;
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public BankDetailsModel updateBankDetails(BankDetailsDTO bankDetailsDTO, Long id, String token) {
		Long bankAccountId = tokenUtil.decodeToken(token);
		Optional<AdminModel> isTokenPresent = adminRepository.findById(bankAccountId);
		if(isTokenPresent.isPresent()) {
			Optional<AdminModel> admin = adminRepository.findById(id);
			Optional<BankDetailsModel> isBankDetailsPresent = bankDetailsRepository.findById(id);
			if(admin.isPresent()) {
				isBankDetailsPresent.get().setUpdatedUser(admin.get());
			}
			if(isBankDetailsPresent.isPresent()) {
				isBankDetailsPresent.get().setBankName(bankDetailsDTO.getBankName());
				isBankDetailsPresent.get().setAccountNo(bankDetailsDTO.getAccountNo());
				isBankDetailsPresent.get().setIfscCode(bankDetailsDTO.getIfscCode());
				isBankDetailsPresent.get().setAccountHolderName(bankDetailsDTO.getAccountHolderName());
				isBankDetailsPresent.get().setBranch(bankDetailsDTO.getBranch());
				isBankDetailsPresent.get().setLinkedMobNo(bankDetailsDTO.getLinkedMobNo());
				isBankDetailsPresent.get().setEmail(bankDetailsDTO.getEmail());
				isBankDetailsPresent.get().setCreatorDateTime(bankDetailsDTO.getCreatorDateTime().now());
				isBankDetailsPresent.get().setUpdatedDateTime(bankDetailsDTO.getUpdatedDateTime().now());
				bankDetailsRepository.save(isBankDetailsPresent.get());
				String body = "BankDetails is updated succesfully with id " + isBankDetailsPresent.get().getId();
				String subject = "BankDetails updated Succesfully";
				mailService.send(isBankDetailsPresent.get().getEmail(), subject, body);
				return isBankDetailsPresent.get();
			}
			throw new CustomNotFoundException(400, "not present");
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public Optional<BankDetailsModel> getBankDetailsById(Long id, String token) {		
		return bankDetailsRepository.findById(id);
	}
	
	@Override
	public BankDetailsModel deleteBankDetails(Long id, String token) {
		Long bankAccountId = tokenUtil.decodeToken(token);
		Optional<BankDetailsModel> isBankDetailsPresent = bankDetailsRepository.findById(bankAccountId);
		if(isBankDetailsPresent.isPresent()) {
			bankDetailsRepository.delete(isBankDetailsPresent.get());
			return isBankDetailsPresent.get();
		}
		throw new CustomNotFoundException(400, "Bank details not found");
	}
}
