package com.bala.springbootsecurity.service;

import com.bala.springbootsecurity.model.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);
}
