package com.bridgelabz.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lms.model.MentorsModel;
@Repository
public interface MentorsRepository extends JpaRepository<MentorsModel, Long> {

	List<MentorsModel> findByMentorRole(String role);

}
