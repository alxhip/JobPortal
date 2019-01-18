package com.job.dao;

import java.util.List;

import com.job.entity.Job;
import com.job.entity.User;



public interface JobDAO {

	public List<Job> findAll();
	
	public Job findById(int theId);
	
	public void save(Job theJob);
	
	public void deleteById(int theId);
	
	public List<Job> getJobsPulishedByUser(String string);
	
	public int getUserId(String username);
	
	public User getUser(int theId);

	public void createApllication(int userId, int jobId);
	
	public void saveUser(User user);
	
	public List<Job> searchJob(String input);

	public boolean hasAlreadyApplied(User user, Job job);
}
