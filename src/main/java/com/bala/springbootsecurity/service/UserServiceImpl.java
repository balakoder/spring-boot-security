package com.bala.springbootsecurity.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bala.springbootsecurity.dao.UserDao;
import com.bala.springbootsecurity.model.Role;
import com.bala.springbootsecurity.model.User;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user)
	{

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		@SuppressWarnings("unchecked")
		Set<Role> roles = new HashSet<>();
		roles.add(userDao.findByRole("ROLE_USER"));
		user.setRoles(roles);
		user.setRoles(new HashSet<>(userDao.getAllRoles()));
		userDao.save(user);
	}

	@Override
	public User findByUsername(String username)
	{
		return userDao.getByUsername(username);
	}
}
