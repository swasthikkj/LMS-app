package com.bridgelabz.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lms.model.TechStackModel;
@Repository
public interface TechStackRepository extends JpaRepository<TechStackModel, Long> {

}
