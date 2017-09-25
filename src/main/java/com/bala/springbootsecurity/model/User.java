package com.bala.springbootsecurity.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotEmpty;
 

@Entity
@Table(name = "user")
@Proxy(lazy=false)
public class User {

 
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",
	  strategy = "uuid")
    @Column(name = "id")
    private String id;
  //@NotEmpty(message = "Please enter your first Name")
   
    
  

public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

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

    //@NotEmpty(message = "Please enter your phone")
     @Column(name = "phone")
    private String phone;
   

	@Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "last_updated")
    private Date lastUpdated;
    
    private String passwordConfirm;
    private Set<Role> roles;
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    @Access(AccessType.PROPERTY)
    @ManyToMany
    @JoinTable(name = "user_role", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public User() { }

     
    public User(String email,String password ,String fname,String lname,String phone) {
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
      }
    
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
   

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
   

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

       
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
 
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
 
  //  @OneToMany//(cascade = CascadeType.ALL)
	//@JoinTable(name = "user_expense", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "expense_id") })
	
 
  
	
}