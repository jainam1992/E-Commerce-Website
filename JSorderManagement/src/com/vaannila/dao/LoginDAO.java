package com.vaannila.dao;

import java.util.List;

import com.vaannila.domain.logindetails;

public interface LoginDAO {
	public List<logindetails> getLoginData(String userName);
}
