package com.ambula.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ambula.model.AdminLogin;
import com.ambula.service.AdminLoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminLoginController {

	@Autowired
	private AdminLoginService adminLoginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> userLogInToAccount(@RequestBody @Valid AdminLogin adminLogin) {
		String result = adminLoginService.adminLogIn(adminLogin);
		
		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> userLogOutFromAccount(@RequestParam String key) {
		String result = adminLoginService.adminLogOut(key);
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
