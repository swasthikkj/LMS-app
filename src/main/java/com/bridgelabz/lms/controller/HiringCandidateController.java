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
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.lms.dto.HiringCandidateDTO;
import com.bridgelabz.lms.model.HiringCandidateModel;
import com.bridgelabz.lms.service.ICandidateHiringService;
/**
 * Purpose:create hiring candidate controller
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */
@RestController
@RequestMapping("/hirecandidate")
public class HiringCandidateController {
	@Autowired
	ICandidateHiringService hireCandidateService;
	/**
	 * Purpose:add hiring candidate
	 * @Param token 
	 */
	@PostMapping("/addHireCandidate")
	public HiringCandidateModel addHireCandidate(@Valid @RequestBody HiringCandidateDTO hireCandidateDTO, Long bankId, @RequestHeader String token) {
		return hireCandidateService.addHireCandidate(hireCandidateDTO, bankId, token);		
	}
	/**
	 * Purpose:update hiring candidate by id
	 * @Param token and id
	 */
	@PutMapping("updateHireCandidate/{id}")
	public HiringCandidateModel updateHireCandidate(@Valid @RequestBody HiringCandidateDTO hireCandidateDTO, @PathVariable Long id, @RequestHeader String token) {
		return hireCandidateService.updateHireCandidate(hireCandidateDTO, id, token);
	}
	/**
	 * Purpose:get hiring candidate by id
	 * @Param token and id
	 */
	@GetMapping("/getCandidateData/{id}")
    public Optional<HiringCandidateModel> getCandidateById(@PathVariable Long id, @RequestHeader String token) {
        return hireCandidateService.getHireCandidateById(id, token);
    }
	/**
	 * Purpose:get all hiring candidate
	 * @Param token 
	 */
	@GetMapping("/getAllCandidates")
	public List<HiringCandidateModel> getAllCandidates(@RequestHeader String token) {
		return hireCandidateService.getAllHireCandidates(token);	
	}
	/**
	 * Purpose:delete hiring candidate by id
	 * @Param token and id
	 */
	@DeleteMapping("/deleteCandidate/{id}")
	public HiringCandidateModel deleteCandidate(@PathVariable Long id,  @RequestHeader String token) {
		return hireCandidateService.deleteHireCandidate(id, token);
	}
}
