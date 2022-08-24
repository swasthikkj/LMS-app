package com.bridgelabz.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lms.model.BankDetailsModel;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetailsModel, Long>{
	
}
