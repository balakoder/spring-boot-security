package com.bala.springbootsecurity.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bala.springbootsecurity.model.Role;
import com.bala.springbootsecurity.model.User;

import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(User user) {
		getSession().save(user);
		log.info("user saved successfully !!!");
	}

	public void delete(User user) {
		getSession().delete(user);
		log.info("user deleted successfully !!!");
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return getSession().createQuery("from User").list();
	}

	public User getByEmail(String email) {
		log.info("UserDao:getByEmail*.");
		return (User) getSession().createQuery("from User where email = :email").setParameter("email", email)
				.uniqueResult();
	}

	public User getByUsername(String username) {
		log.info("UserDao:getByUsername");
		return (User) getSession().createQuery("from User where username = :username")
				.setParameter("username", username).uniqueResult();
	}

	public User getById(String id) {
		return getSession().get(User.class, id);
	}

	public void update(User user) {
		getSession().update(user);
	}

	public HashSet<Role> getAllRoles() {
		log.info("UserDao:getAllRoles");
		@SuppressWarnings("unchecked")
		List<Role> users = getSession().createCriteria(Role.class).list();
		Set<Role> roles = new HashSet<>(users);
		return (HashSet<Role>) roles;

	}

	public Role findByRole(String role) {
		return (Role) getSession().createQuery("from Role where name = :role").setParameter("role", role)
				.uniqueResult();
	}

}