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

import com.bridgelabz.lms.dto.TechStackDTO;
import com.bridgelabz.lms.model.TechStackModel;
import com.bridgelabz.lms.service.ITechStackService;
/**
 * Purpose:create tech stack controller
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */
@RestController
@RequestMapping("/techstackModule")
public class TechStackController {
	@Autowired
	ITechStackService techService;
	/**
	 * Purpose:add tech stack
	 * @Param token 
	 */
	@PostMapping("/addTechStack")
	public TechStackModel addTechStack(@Valid @RequestBody TechStackDTO techStackDTO, @RequestHeader String token, @RequestParam List<Long> adminId) {
		return techService.addTechStack(techStackDTO, token, adminId);	
	}
	/**
	 * Purpose:update tech stack by id
	 * @Param token and id
	 */
	@PutMapping("updateTechStack/{id}")
	public TechStackModel updateTechStack(@Valid @RequestBody TechStackDTO techStackDTO, @PathVariable Long id, @RequestHeader String token) {
		return techService.updateTechStack(techStackDTO, id, token);
	}
	/**
	 * Purpose:fetch tech stack by id
	 * @Param token and id
	 */
	@GetMapping("/getTechStack/{id}")
    public Optional<TechStackModel> getTechStackById(@PathVariable Long id, @RequestHeader String token) {
        return techService.getTechById(id, token);
    }
	/**
	 * Purpose:fetch all tech stacks
	 * @Param token 
	 */
	@GetMapping("/getAllTechStacks")
	public List<TechStackModel> getAllTechStack(@RequestHeader String token) {
		return techService.getAllTechStacks(token);	
	}
	/**
	 * Purpose:delete tech stack by id
	 * @Param token and id
	 */
	@DeleteMapping("/deleteTechStack/{id}")
	public TechStackModel deleteTechStack(@PathVariable Long id,  @RequestHeader String token) {
		return techService.deleteTechStack(id, token);
	}
}
