package com.lym.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.lym.entity.User;

@Repository
public class UserDao extends BaseDaoImpl<User, Serializable>{

	public UserDao() {
		super(User.class);
	}
	
}
