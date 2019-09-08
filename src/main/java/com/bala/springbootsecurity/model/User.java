package com.bala.springbootsecurity.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
@Proxy(lazy = false)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id")
	private String id;

	@NotEmpty(message = "Please enter your password")
	@NotNull
	@Column(name = "password")
	private String password;

	@NotEmpty(message = "Please enter your email")
	@Column(name = "email")
	private String email;

	@Column(name = "fname")
	private String fname;

	@Column(name = "username")
	private String username;

	@Column(name = "lname")
	private String lname;

	@Column(name = "phone")
	private String phone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated")
	private Date lastUpdated;

	@Transient
	private String passwordConfirm;

	@Access(AccessType.PROPERTY)
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@PrePersist
	protected void onCreate() {
		lastUpdated = dateCreated = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		lastUpdated = new Date();
	}

	public User() {
	}

	public User(String email, String password, String fname, String lname, String phone) {
		this.email = email;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
	}

}