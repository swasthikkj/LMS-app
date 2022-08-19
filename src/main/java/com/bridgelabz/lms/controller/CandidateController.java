package com.bridgelabz.lms.controller;

import java.util.List;
import java.util.Optional;

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

import com.bridgelabz.lms.dto.CandidateDTO;
import com.bridgelabz.lms.model.CandidateModel;
import com.bridgelabz.lms.service.ICandidateService;


@RestController
@RequestMapping("/candidatemodule")
public class CandidateController {
	@Autowired
	ICandidateService candidateService;
	
	@PostMapping("/addCandidate")
	public CandidateModel addCandidate(@RequestBody CandidateDTO candidateDTO, @RequestHeader String token) {
		return candidateService.addCandidate(candidateDTO, token);		
	}
	
	@PutMapping("updateCandidate/{id}")
	public CandidateModel updateCandidate(@RequestBody CandidateDTO candidateDTO, @PathVariable Long id, @RequestHeader String token) {
		return candidateService.updateCandidate(candidateDTO, id, token);
	}
	
	@GetMapping("/getCandidateData/{id}")
    public Optional<CandidateModel> getCandidateById(@PathVariable Long id, @RequestHeader String token) {
        return candidateService.getCandidateById(id, token);
    }
	
	@GetMapping("/getAllCandidates")
	public List<CandidateModel> getAllCandidates(@RequestHeader String token) {
		return candidateService.getAllCandidates(token);	
	}
	
	@DeleteMapping("/deleteCandidate/{id}")
	public CandidateModel deleteCandidate(@PathVariable Long id,  @RequestHeader String token) {
		return candidateService.deleteCandidate(id, token);
	}
	
	@GetMapping("/getCandidate/{status}")
	public List<CandidateModel> getCandidateByStatus(@RequestHeader String status) {
		return candidateService.getCandidateByStatus(status);		
	}
	
	@PutMapping("/changeStatus/{id}")
	public CandidateModel candidateStatus(@PathVariable("id") Long id,@RequestParam String status) {
		return candidateService.changeStatus(id, status);		
	}
}
