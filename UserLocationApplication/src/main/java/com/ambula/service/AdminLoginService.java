package com.ambula.service;

import com.ambula.model.AdminLogin;

public interface AdminLoginService {

	public String adminLogIn(AdminLogin adminLogin);
	public String adminLogOut(String key);
}
