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

import com.bridgelabz.lms.dto.AdminDTO;
import com.bridgelabz.lms.model.AdminModel;
import com.bridgelabz.lms.service.IAdminService;
import com.bridgelabz.lms.util.Response;

/**
 * Purpose:create admin controller
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@RestController
@RequestMapping("/adminmodule")
public class AdminController {
	@Autowired
	IAdminService adminService;
	/**
	 * Purpose:add admin
	 */
	@PostMapping("/addAdmin")
	public AdminModel addAdmin(@Valid @RequestBody AdminDTO adminDTO) {
		return adminService.addAdmin(adminDTO);		
	}
	/**
	 * Purpose:update admin
	 */
	@PutMapping("updateAdmin/{id}")
	public AdminModel updateAdmin(@Valid @RequestBody AdminDTO adminDTO, @PathVariable Long id, @RequestHeader String token) {
		return adminService.updateAdmin(adminDTO, id, token);
	}
	/**
	 * Purpose:get admin by id
	 */
	@GetMapping("/getAdminData/{id}")
    public Optional<AdminModel> getAdminById(@PathVariable Long id, @RequestHeader String token) {
        return adminService.getAdminById(id, token);
    }
	/**
	 * Purpose:get all admins
	 */
	@GetMapping("/getAllAdmins")
	public List<AdminModel> getAllAdmins(String token) {
		return adminService.getAllAdmins(token);	
	}
	/**
	 * Purpose:delete admin
	 */
	@DeleteMapping("deleteAdmin/{id}")
	public AdminModel deleteAdmin(@PathVariable Long id,  @RequestHeader String token) {
		return adminService.deleteAdmin(id, token);
	}
	/**
	 * Purpose:login to generate token
	 * @Param enter email and password
	 */
	@PostMapping("/login")
    public Response login(@RequestParam String emailId, @RequestParam String password) {
        return adminService.login(emailId, password);
    }
	/**
	 * Purpose:reset admin password
	 * @Param email
	 */
	@PostMapping("/resetpassword")
    public Response resetPassword(@RequestParam String emailId) {
        return adminService.resetPassword(emailId);
    }
	/**
	 * Purpose:change admin password
	 * @Param password
	 */
    @PutMapping("/changepassword/{token}")
    public AdminModel changePassword(@PathVariable("token") String token, @RequestParam String password) {
        return adminService.changePassword(token, password);
    }
}
