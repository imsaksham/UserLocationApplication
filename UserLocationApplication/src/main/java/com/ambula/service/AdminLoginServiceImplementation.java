package com.ambula.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ambula.repository.AdminSessionDao;
import com.ambula.repository.UserLocationDao;
import com.ambula.exception.LoginException;
import com.ambula.model.AdminLogin;
import com.ambula.model.CurrentAdminSession;
import com.ambula.model.UserLocation;

@Service
public class AdminLoginServiceImplementation implements AdminLoginService {
	
	@Autowired
	private UserLocationDao userLocationDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;

	@Override
	public String adminLogIn(AdminLogin adminLogin) {
		UserLocation existingUser = userLocationDao.findByName(adminLogin.getUsername());
		
		if (existingUser == null) {
			throw new LoginException("Please enter a valid username");
		}
		
		
		CurrentAdminSession opt = adminSessionDao.findByUsername(existingUser.getName());
		
		if (opt != null) {
			throw new LoginException("User already logged in");
		}
		
		if (existingUser.getPassword().equals(adminLogin.getPassword())) {
			
			String key = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
			
			CurrentAdminSession activeSession = new CurrentAdminSession(existingUser.getName(), key, LocalDateTime.now());
			
			adminSessionDao.save(activeSession);
			
			return activeSession.toString();
		}
		else {
			throw new LoginException("Please enter a valid password");
		}
	}

	@Override
	public String adminLogOut(String key) {
		
		CurrentAdminSession activeSession = adminSessionDao.findByUuid(key);
		
		if (activeSession == null) {
			throw new LoginException("User not logged in");
		}
		
		adminSessionDao.delete(activeSession);
		
		return "Successfully Logged out!";
	}

}
