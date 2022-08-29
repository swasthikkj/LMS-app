package com.bridgelabz.lms.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
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

/**
 * Purpose:create candidate controller
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */
@RestController
@RequestMapping("/candidatemodule")
public class CandidateController {
	@Autowired
	ICandidateService candidateService;
	
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@PostMapping("/importCandidates")
	public void importCsvToDBJob() {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("startAs",System.currentTimeMillis()).toJobParameters();
		try {
			jobLauncher.run(job,jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobParametersInvalidException |
				JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Purpose:add candidate
	 * @Param token 
	 */

	@PostMapping("/addCandidate")
	public CandidateModel addCandidate(@Valid @RequestBody CandidateDTO candidateDTO, @RequestHeader String token, @RequestParam Long techId) {
		return candidateService.addCandidate(candidateDTO, token, techId);		
	}
	/**
	 * Purpose:update candidate
	 * @Param token and id
	 */
	@PutMapping("updateCandidate/{id}")
	public CandidateModel updateCandidate(@Valid @RequestBody CandidateDTO candidateDTO, @PathVariable Long id, @RequestHeader String token) {
		return candidateService.updateCandidate(candidateDTO, id, token);
	}
	/**
	 * Purpose:get candidate by id
	 * @Param token and id
	 */
	@GetMapping("/getCandidateData/{id}")
	public Optional<CandidateModel> getCandidateById(@PathVariable Long id, @RequestHeader String token) {
		return candidateService.getCandidateById(id, token);
	}
	/**
	 * Purpose:get all candidates
	 * @Param token 
	 */
	@GetMapping("/getAllCandidates")
	public List<CandidateModel> getAllCandidates(@RequestHeader String token) {
		return candidateService.getAllCandidates(token);	
	}
	/**
	 * Purpose:delete candidate
	 * @Param token and id
	 */
	@DeleteMapping("/deleteCandidate/{id}")
	public CandidateModel deleteCandidate(@PathVariable Long id,  @RequestHeader String token) {
		return candidateService.deleteCandidate(id, token);
	}
	/**
	 * Purpose:get candidate by status
	 * @Param token and status
	 */
	@GetMapping("/getCandidate/{status}")
	public List<CandidateModel> getCandidateByStatus(@RequestHeader String status, @RequestHeader String token) {
		return candidateService.getCandidateByStatus(status, token);		
	}
	/**
	 * Purpose:change candidate status
	 * @Param token, id and status
	 */
	@PutMapping("/changeStatus/{id}")
	public CandidateModel candidateStatus(@PathVariable("id") Long id, @RequestParam String status, @RequestHeader String token) {
		return candidateService.changeStatus(id, status, token);		
	}
	/**
	 * Purpose:get candidate status count
	 * @Param token and status
	 */
	@GetMapping("/getStatusCount")
	public long statusCount(@RequestHeader String status, @RequestHeader String token) {
		return candidateService.statusCount(status, token);
	}
}
