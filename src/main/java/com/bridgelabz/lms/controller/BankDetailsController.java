package com.bridgelabz.lms.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.lms.dto.BankDetailsDTO;
import com.bridgelabz.lms.model.BankDetailsModel;
import com.bridgelabz.lms.model.CandidateModel;
import com.bridgelabz.lms.service.IBankDetailsService;
/**
 * Purpose:create bank details controller
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */
@RestController
@RequestMapping("/bankdetailsmodule")
public class BankDetailsController {
	@Autowired
	IBankDetailsService bankDetailsService;
	/**
	 * Purpose:add bank account 
	 * @Param token and admin id
	 */
	@PostMapping("/addBankAccount")
	public BankDetailsModel addBankDetails(@Valid @RequestBody BankDetailsDTO bankDetailsDTO, @RequestParam Long adminId, @RequestHeader String token) {
		return bankDetailsService.addBankDetails(bankDetailsDTO, adminId, token);		
	}
	/**
	 * Purpose:update bank account 
	 * @Param token and id
	 */
	@PutMapping("updateBankDetails/{id}")
	public BankDetailsModel updateCandidate(@Valid @RequestBody BankDetailsDTO bankDetailsDTO, @PathVariable Long id, @RequestHeader String token) {
		return bankDetailsService.updateBankDetails(bankDetailsDTO, id, token);
	}
	/**
	 * Purpose:fetch bank account by id 
	 * @Param token and id
	 */
	@GetMapping("/getBankDetails/{id}")
    public Optional<BankDetailsModel> getBankDetailsById(@PathVariable Long id, @RequestHeader String token) {
        return bankDetailsService.getBankDetailsById(id, token);
    }
	/**
	 * Purpose:delete bank account 
	 * @Param token and id
	 */
	@DeleteMapping("/deletebankDetails/{id}")
	public BankDetailsModel deleteBankDetails(@PathVariable Long id,  @RequestHeader String token) {
		return bankDetailsService.deleteBankDetails(id, token);
	}
}
