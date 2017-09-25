package com.bala.springbootsecurity.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import com.bala.springbootsecurity.model.*;
import javax.transaction.Transactional;
 
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private SessionFactory _sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(User user) {
		logger.info("****************save user*****************************.", "save");
		getSession().save(user);
		getSession().flush();
		return;
	}

	public void delete(User user) {
		getSession().delete(user);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return getSession().createQuery("from User").list();
	}

	public User getByEmail(String email) {
		logger.info("****************getByEmail*****************************.", "getByEmail");
		return (User) getSession().createQuery("from User where email = :email").setParameter("email", email)
				.uniqueResult();
		// return (users != null && !users.isEmpty()) ? users.get(0) : null;
	}

	public User getByUsername(String username) {
		logger.info("****************getByEmail*****************************.", "getByUsername");
		return (User) getSession().createQuery("from User where username = :username")
				.setParameter("username", username).uniqueResult();
		// return (users != null && !users.isEmpty()) ? users.get(0) : null;
	}

	public User getById(String id) {
		return (User) getSession().get(User.class, id);
	}

	public void update(User user) {
		getSession().update(user);
		return;
	}

	public HashSet<Role> getAllRoles() {
		logger.info("****************inside  getAllRoles*****************************.", "getAllRoles");
		List users = getSession().createCriteria(Role.class).list();

		Set<Role> roles = new HashSet<Role>(users);
		return (HashSet<Role>) roles;

	}
	
}