package com.ambula.service;

import java.util.List;

import com.ambula.model.UserLocation;

public interface UserLocationService {

	public UserLocation createUser(UserLocation user);
	public String updateUser(UserLocation user, String name, String key);
	public List<UserLocation> getUsers(int count);
}
