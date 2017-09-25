package com.bala.springbootsecurity.model;
 

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
   
	
	
	   
    @Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",
	  strategy = "uuid")
    @Column(name = "id")
    private String id; 

    @Column(name = "name")
    private String name;
	
    private Set<User> users;
 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Access(AccessType.PROPERTY)
    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

