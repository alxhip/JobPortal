package com.job.service;

import java.util.List;

import com.job.entity.Job;
import com.job.entity.User;


public interface JobService {

	public List<Job> findAll();
	
	public Job findById(int theId);
	
	public void save(Job thejob);
	
	public void deleteById(int theId);

	public List<Job> getJobsPulishedByUser(String string);

	public int getUserId(String username);
	
	public User getUser(int userId);

	public void createApplication(int userId, int jobId);
	
	public void saveUser(User user);
	
	public List<Job> searchJob(String input);

	public boolean hasAlreadyApplied(User user, Job job);
}
