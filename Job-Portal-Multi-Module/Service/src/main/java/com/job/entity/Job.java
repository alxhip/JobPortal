package com.job.entity;

import java.sql.Timestamp;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "publishedDate")
	private Timestamp publishedDate;
	
	@JsonBackReference
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "users_id")
	private User user;
	
	@JsonBackReference
	@OneToMany(mappedBy="job",
			   cascade= { CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<Application> applications;
	
	
	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job() {
	}

	public Job(String title, String description, Timestamp publishedDate) {
		this.title = title;
		this.description = description;
		this.publishedDate = publishedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Timestamp publishedDate) {
		this.publishedDate = publishedDate;
	}

	@Override
	public String toString() {
		return "Jobs [id=" + id + ", Title=" + title + ", description=" + description + ", publishedDate="
				+ publishedDate + "]";
	}

}
