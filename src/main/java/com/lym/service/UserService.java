package com.lym.service;

import com.lym.entity.User;

public interface UserService {

	public void createUser(String username, String password);
	
	public User findUserByUsername(String username);
	
}
