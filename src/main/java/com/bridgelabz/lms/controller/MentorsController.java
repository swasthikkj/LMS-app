package com.bridgelabz.lms.controller;

import java.util.List;
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

import com.bridgelabz.lms.dto.MentorsDTO;
import com.bridgelabz.lms.model.MentorsModel;
import com.bridgelabz.lms.service.IMentorsService;
@RestController
@RequestMapping("/mentorsModule")
public class MentorsController {
	@Autowired
	IMentorsService mentorsService;
	
	@PostMapping("/addMentor")
	public MentorsModel addMentor(@Valid @RequestBody MentorsDTO mentorsDTO, @RequestHeader String token) {
		return mentorsService.addMentor(mentorsDTO, token);	
	}
	
	@PutMapping("updateMentor/{id}")
	public MentorsModel updateMentor(@Valid @RequestBody MentorsDTO mentorsDTO, @PathVariable Long id, @RequestHeader String token) {
		return mentorsService.updateMentor(mentorsDTO, id, token);
	}
	
	@GetMapping("/getMentorData/{id}")
    public Optional<MentorsModel> getMentorById(@PathVariable Long id, @RequestHeader String token) {
        return mentorsService.getMentorById(id, token);
    }
	
	@GetMapping("/getAllMentors")
	public List<MentorsModel> getAllMentors(@RequestHeader String token) {
		return mentorsService.getAllMentors(token);	
	}
	
	@DeleteMapping("/deleteMentor/{id}")
	public MentorsModel deleteMentor(@PathVariable Long id,  @RequestHeader String token) {
		return mentorsService.deleteMentor(id, token);
	}
	
	@GetMapping("/getMentorByRole")
	public List<MentorsModel> getMentorsByRole(@RequestParam String role, @RequestHeader String token) {
		return mentorsService.getMentorsByRole(role, token);		
	}
	
	@GetMapping("/getMentorsCount")
	public long mentorsRoleCount(@RequestParam String mentorRole, @RequestHeader String token) {
		return mentorsService.mentorsRoleCount(mentorRole, token);
	}
}
