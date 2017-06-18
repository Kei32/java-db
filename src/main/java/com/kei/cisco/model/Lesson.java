package com.kei.cisco.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="LESSON")
public class Lesson {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Column(name="NAME", unique=true, nullable=false)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "LESSON_TEST",
             joinColumns = { @JoinColumn(name = "LESSON_ID") },
             inverseJoinColumns = { @JoinColumn(name = "TEST_ID") })
	private Set<Test> tests = new HashSet<Test>();

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

	public Set<Test> getTests() {
		return tests;
	}

	public void setTests(Set<Test> tests) {
		this.tests = tests;
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + ", name=" + name + ", tests=" + tests +"]";
	}

	
}
