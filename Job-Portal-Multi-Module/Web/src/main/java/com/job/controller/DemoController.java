package com.job.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.job.entity.Job;
import com.job.entity.User;
import com.job.service.JobService;

@Controller
@RequestMapping("job")
public class DemoController {

	@Autowired
	JobService jobService;

	@Autowired
	private SessionRegistry sessionRegistry;

	/*
	 * @Autowired private EntityManager sessionRegistry;
	 */
	@RequestMapping("/showHome")
	public String showHome(Model model) {
		List<Job> latestJobs = jobService.findAll();
		model.addAttribute("jobs", latestJobs);
		return getHomePage();

	}

	@RequestMapping("/detailedJobView")
	public String detailedJobView(@RequestParam int jobId, Model model) {
		System.out.println();
		Job currentJob = jobService.findById(jobId);
		model.addAttribute("job", currentJob);

		return "jobView";

	}

	@RequestMapping("/listJobsPublishedByUser")
	public String listJobsPublishedByUser(Model model) {

		model.addAttribute("jobs", jobService.getJobsPulishedByUser(getUsername()));
		return "jobsPublishedByUser";
	}

	@RequestMapping("/jobForm")
	public String jobForm(@RequestParam int jobId, Model model) {
		model.addAttribute("job", jobService.findById(jobId));
		return "jobForm";
	}

	@RequestMapping("/addJob")
	public String JobFrom(Model model) {
		Job job = new Job();
		job.setUser(jobService.getUser(jobService.getUserId(getUsername())));
		model.addAttribute("job", job);
		return "jobForm";
	}

	@RequestMapping("/deleteJob")
	public String deleteJob(@RequestParam int jobId) {
		jobService.deleteById(jobId);
		if (getUsername().equals("Admin")) {
			return "redirect:/job/showHome";
		} else
			return "redirect:/job/listJobsPublishedByUser";
	}

	@RequestMapping("/saveJob")
	public String saveJob(@ModelAttribute("job") Job job) {
		jobService.save(job);
		if (getUsername().equals("Admin")) {
			return "redirect:/job/showHome";
		} else
			return "redirect:/job/listJobsPublishedByUser";
	}

	@RequestMapping("/application")
	public String application(@RequestParam("jobId") int jobId, Model model) {
		int userId = jobService.getUserId(getUsername());
		if (jobService.hasAlreadyApplied(jobService.getUser(userId), jobService.findById(jobId))) {
			model.addAttribute("hasAlreadyApplied",true);
			Job job= jobService.findById(jobId);
			model.addAttribute("job", job);
			System.out.println(job);
			return "jobView";
		}
		else {
		jobService.createApplication(userId, jobId);
		return "redirect:/job/showHome";}
	}

	@RequestMapping("/showProfile")
	public String showProfile(Model model) {
		model.addAttribute("profile", jobService.getUser(jobService.getUserId(getUsername())));
		return "profileForm";
	}

	@RequestMapping("/saveProfile")
	public String saveProfile(@ModelAttribute("profile") User user, HttpServletRequest request,
			HttpServletResponse response) {
		jobService.saveUser(user);
		List<SessionInformation> infos = sessionRegistry.getAllSessions(user, false);

		for (SessionInformation info : infos) {
			info.expireNow(); // expire the session
			sessionRegistry.removeSessionInformation(info.getSessionId());
		}
		return "redirect:/login?logout";
	}

	@RequestMapping("/searchJob")
	public String searchJob(@RequestParam("input") String input, Model model) {

		model.addAttribute("jobs", jobService.searchJob(input));
		return getHomePage();
	}

	public String getUsername() {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	public String getHomePage() {
		String targetUrl = "";
		if (getUsername().equals("Admin")) {
			targetUrl = "adminIndex";
		} else {
			targetUrl = "userIndex";
		}

		return targetUrl;
	}

}
