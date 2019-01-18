package com.job.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.entity.Job;
import com.job.service.JobService;



@RestController
@RequestMapping("/api")
public class JobRestController {

	private JobService jobService;
	
	@Autowired
	public JobRestController(JobService thejobService) {
		jobService = thejobService;
	}
	
	@GetMapping("/jobs")
	public List<Job> findAll() {
		return jobService.findAll();
	}

	
	@GetMapping("/jobs/{jobId}")
	public Job getjob(@PathVariable int jobId) {
		
		Job thejob = jobService.findById(jobId);
		
		if (thejob == null) {
			throw new RuntimeException("job id not found - " + jobId);
		}
		
		return thejob;
	}
	
	
	@PostMapping("/jobs")
	public Job addjob(@RequestBody Job thejob) {
		
		thejob.setId(0);
		
		jobService.save(thejob);
		
		return thejob;
	}
	
	
	@PutMapping("/jobs")
	public Job updatejob(@RequestBody Job thejob) {
		
		jobService.save(thejob);
		
		return thejob;
	}
	
	
	@DeleteMapping("/jobs/{jobId}")
	public String deletejob(@PathVariable int jobId) {
		
		Job tempjob = jobService.findById(jobId);
		
		
		if (tempjob == null) {
			throw new RuntimeException("job id not found - " + jobId);
		}
		
		jobService.deleteById(jobId);
		
		return "Deleted job id - " + jobId;
	}
	
}










