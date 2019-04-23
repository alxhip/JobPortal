package com.job.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@Autowired
	private JobService jobService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/jobs")
	public List<Job> findAll() {
		return jobService.findAll();
	}

	@GetMapping("/jobs/{jobId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Job getjob(@PathVariable int jobId) {

		Job thejob = jobService.findById(jobId);

		if (thejob == null) {
			throw new RuntimeException("job id not found - " + jobId);
		}

		return thejob;
	}

	@CrossOrigin("http://localhost:4200")
	@PostMapping("/jobs")
	public Job addjob(@RequestBody Job thejob) {
		
		thejob.setId(0);
		jobService.save(thejob);
		return thejob;
	}

	@CrossOrigin("http://localhost:4200")
	@PutMapping("/jobs")
	public Job updatejob(@RequestBody Job thejob) {
		System.out.println(thejob);
		jobService.save(thejob);

		return thejob;
	}

	@CrossOrigin("http://localhost:4200")
	@DeleteMapping("/jobs/{jobId}")
	public String deletejob(@PathVariable int jobId) {

		Job tempjob = jobService.findById(jobId);

		if (tempjob == null) {
			throw new RuntimeException("job id not found - " + jobId);
		}

		jobService.deleteById(jobId);

		return "Deleted job id - " + jobId;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{userId}")
	public List<Job> listJobsPublishedByUser(@PathVariable("userId") Long id) {
		return jobService.getJobsPulishedByUserId(id);
	}

}
