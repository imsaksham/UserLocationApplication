package com.ambula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ambula.model.UserLocation;
import com.ambula.service.UserLocationService;

@RestController
public class UserLocationController {

	@Autowired
	private UserLocationService userLocationService;
	
	@PostMapping("/create_data")
	public ResponseEntity<UserLocation> createUser(@RequestBody UserLocation user) {
		UserLocation registeredUser = userLocationService.createUser(user);
		
		return new ResponseEntity<UserLocation>(registeredUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/update_data")
	public ResponseEntity<String> updateUser(@RequestBody UserLocation user, @RequestParam String name, @RequestParam String key) {
		String updatedUser = userLocationService.updateUser(user, name, key);
		
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@GetMapping("/get_users/{N}")
	public ResponseEntity<List<UserLocation>> getUsers(@PathVariable("N") int N) {
		List<UserLocation> result = userLocationService.getUsers(N);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
