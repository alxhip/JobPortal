package com.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.job.dao.JobDAO;
import com.job.entity.Job;
import com.job.entity.User;



@Service
public class JobServiceImpl implements JobService {

	private JobDAO jobDAO;
	
	@Autowired
	public JobServiceImpl(JobDAO thejobDAO) {
		jobDAO = thejobDAO;
	}
	
	@Override
	@Transactional
	public List<Job> findAll() {
		return jobDAO.findAll();
	}

	@Override
	@Transactional
	public Job findById(int theId) {
		return jobDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Job thejob) {
		jobDAO.save(thejob);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		jobDAO.deleteById(theId);
	}

	@Override
	public List<Job> getJobsPulishedByUser(String string) {
		return jobDAO.getJobsPulishedByUser(string);
	}
	
	@Override
	@Transactional
	public int getUserId(String username) {
		return jobDAO.getUserId(username);
	}
	
	@Override
	@Transactional
	public User getUser(int userId) {
		return jobDAO.getUser(userId);
	}
	
	@Override
	@Transactional
	public void createApplication(int userId, int jobId) {
		jobDAO.createApllication(userId, jobId);
	}
	
	
	@Override
	@Transactional
	public void saveUser(User user) {
		jobDAO.saveUser(user);
	}
	
	@Override
	@Transactional
	public List<Job> searchJob(String input) {
		return jobDAO.searchJob(input);
	}

	@Override
	public boolean hasAlreadyApplied(User user, Job job) {
		return jobDAO.hasAlreadyApplied(user, job);
	}

	
}






