package com.bridgelabz.lms.service;

import java.util.Optional;

import com.bridgelabz.lms.dto.BankDetailsDTO;
import com.bridgelabz.lms.model.BankDetailsModel;

public interface IBankDetailsService {
	BankDetailsModel addBankDetails(BankDetailsDTO bankDetailsDTO, Long adminId, String token);
	
	BankDetailsModel updateBankDetails(BankDetailsDTO bankDetailsDTO, Long id, String token);
	
	Optional<BankDetailsModel> getBankDetailsById(Long id, String token);
	
	BankDetailsModel deleteBankDetails(Long id, String token);

}
