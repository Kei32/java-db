package com.kei.cisco.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CLASS")
public class Class {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Column(name="NAME", unique=true, nullable=false)
	private String name;
	
	@NotEmpty
	@Column(name="DESCRIPTION", nullable=false)
	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CLASS_COURSE",
             joinColumns = { @JoinColumn(name = "CLASS_ID") },
             inverseJoinColumns = { @JoinColumn(name = "COURSE_ID") })
	private Set<Course> courses = new HashSet<Course>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CLASS_USER",
			joinColumns = { @JoinColumn(name = "CLASS_ID") },
			inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	private Set<User> users = new HashSet<User>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Class [id=" + id + ", name=" + name + ", description=" + description
				+ ", courses=" + courses + ", users=" + users +"]";
	}

	
}
