package com.vaannila.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.vaannila.domain.logindetails;

public class LoginDAOImpl implements LoginDAO{

	@SessionTarget
	Session session;
	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	@Override
	public List<logindetails> getLoginData(String userName) {	
		List<logindetails> loginData = null;
		try {
			loginData = session.createQuery("from logindetails where username='"+userName+"'").list();
			System.out.println(userName);
			System.out.println(loginData);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return loginData;
	}
	
}
