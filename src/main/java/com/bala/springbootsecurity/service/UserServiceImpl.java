package com.bala.springbootsecurity.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bala.springbootsecurity.dao.UserDao;
import com.bala.springbootsecurity.model.User;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao; 
  /*  @Autowired
    private UserRepository userRepository;
    
    
    @Autowired
    private RoleRepository roleRepository;
    */
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
         
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(userDao.getAllRoles()));
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.getByUsername(username);
    }
}