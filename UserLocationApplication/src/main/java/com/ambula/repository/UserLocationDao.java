package com.ambula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ambula.model.UserLocation;

@Repository
public interface UserLocationDao extends JpaRepository<UserLocation, Integer> {

	public UserLocation findByName(String name);
}
