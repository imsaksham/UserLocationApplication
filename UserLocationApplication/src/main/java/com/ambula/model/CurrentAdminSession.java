package com.ambula.model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
@jakarta.persistence.Table(name = "Active_Session")
public class CurrentAdminSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sessionId;
	
	@Column(unique = true)
	private String username;

	private String uuid;
	private LocalDateTime localDateTime;
	
	public CurrentAdminSession() {
		
	}
	
	public CurrentAdminSession(Integer userId, String username, String uuid, LocalDateTime localDateTime) {
		this.sessionId = userId;
		this.username = username;
		this.uuid = uuid;
		this.localDateTime = localDateTime;
	}
	
	public CurrentAdminSession(String username, String uuid, LocalDateTime localDateTime) {
		this.username = username;
		this.uuid = uuid;
		this.localDateTime = localDateTime;
	}
}
