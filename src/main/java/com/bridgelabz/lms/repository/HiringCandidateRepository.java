package com.bridgelabz.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lms.model.HiringCandidateModel;
@Repository
public interface HiringCandidateRepository extends JpaRepository<HiringCandidateModel, Long> {

}
