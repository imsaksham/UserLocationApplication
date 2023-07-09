package com.ambula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ambula.model.CurrentAdminSession;

@Repository
public interface AdminSessionDao extends JpaRepository<CurrentAdminSession, Integer> {

	public CurrentAdminSession findByUuid(String uuid);
	public CurrentAdminSession findByUsername(String username);
}
