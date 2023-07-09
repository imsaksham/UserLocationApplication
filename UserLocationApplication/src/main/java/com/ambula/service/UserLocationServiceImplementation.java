package com.ambula.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ambula.exception.LoginException;
import com.ambula.exception.UserAlreadyExistException;
import com.ambula.exception.UserNotFoundException;
import com.ambula.model.CurrentAdminSession;
import com.ambula.model.UserLocation;
import com.ambula.repository.AdminSessionDao;
import com.ambula.repository.UserLocationDao;

@Service
public class UserLocationServiceImplementation implements UserLocationService {
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Autowired
	private UserLocationDao userLocationDao;

	@Override
	public UserLocation createUser(UserLocation user) {
		UserLocation userName =  userLocationDao.findByName(user.getName());
		
		if (userName != null) {
			throw new UserAlreadyExistException("User already exist with this name: " +user.getName());
		}
		else {
			UserLocation savedUser = userLocationDao.save(user);
			
			return savedUser;
		}
	}

	@Override
	public String updateUser(UserLocation user, String name, String key) {
		CurrentAdminSession activeSession = adminSessionDao.findByUuid(key);
		
		if (activeSession == null || !user.getName().equals(activeSession.getUsername())) {
			throw new LoginException("Please Log In first");
		}
		
		UserLocation existingUser = userLocationDao.findByName(name);
		
		if (existingUser == null) {
			throw new UserNotFoundException("User name is not valid");
		}
		
		existingUser.setName(user.getName());
		existingUser.setLatitude(user.getLatitude());
		existingUser.setLongitude(user.getLongitude());
		existingUser.setPassword(user.getPassword());
		
		adminSessionDao.delete(activeSession);
		
		userLocationDao.save(existingUser);
		
		return "Data has been updated successfully. Please Log In again";
	}

	@Override
	public List<UserLocation> getUsers(int count) {
		List<UserLocation> allUserLocations = userLocationDao.findAll();

        List<UserLocation> nearestUsers = allUserLocations.stream()
                .sorted(Comparator.comparingDouble(userLocation ->
                        calculateDistance(userLocation.getLatitude(), userLocation.getLongitude())))
                .limit(count)
                .collect(Collectors.toList());

        return nearestUsers;
	}

	private double calculateDistance(double latitude, double longitude) {
        double x = 0.0;
        double y = 0.0;

        return Math.sqrt(Math.pow(latitude - x, 2) + Math.pow(longitude - y, 2));
    }
	
}
