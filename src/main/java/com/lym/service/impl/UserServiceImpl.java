package com.lym.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lym.dao.UserDao;
import com.lym.entity.User;
import com.lym.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public void createUser(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userDao.save(user);
	}
	
	public User findUserByUsername(String username) {
		List<User> userList = userDao.findBySingleCriteria("username", username);
		if(userList.size() > 0) {
			return userList.get(0);
		}
		
		return null;
	}
	
}
