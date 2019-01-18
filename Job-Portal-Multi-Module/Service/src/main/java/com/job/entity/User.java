package com.job.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToMany(mappedBy="user",
			   cascade= { CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<Application> applications;
	

	/*@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Applications", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "job_id"))
	private List<Job> jobs;*/
	
	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	@OneToMany(mappedBy="user",
			   cascade= { CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<Job> publishedJobs;

	public List<Job> getPublishedJobs() {
		return publishedJobs;
	}

	public void setPublishedJobs(List<Job> publishedJobs) {
		this.publishedJobs = publishedJobs;
	}

	public User(String username, String password, Role role) {
		this.enabled = true;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	

}
