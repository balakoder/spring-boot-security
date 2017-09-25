package com.bala.springbootsecurity.service;
 
 
import com.bala.springbootsecurity.dao.UserDao;
import com.bala.springbootsecurity.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    //@Autowired
    //private UserRepository userRepository;
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    
	@Autowired
	private UserDao userDao;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    	logger.info("*******************userDao.getByEmail(username)********************", "userDao.getByEmail(username)");
    	System.out.println("username is"+username);
        User user = userDao.getByEmail(username);
         
        System.out.println("User is *********"+user);
        System.out.println();
        System.out.println();
        
        if( user == null )
            throw new UsernameNotFoundException( "Oops!" );
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        logger.info("before org.springframework.security.core.userdetails", "userDao.getByEmail(username)");
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        System.out.println(grantedAuthorities);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
