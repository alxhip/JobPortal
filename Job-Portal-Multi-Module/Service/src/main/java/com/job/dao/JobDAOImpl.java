package com.job.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.job.entity.Job;
import com.job.entity.User;
import com.job.entity.Role;
import com.job.entity.Application;

@Repository
public class JobDAOImpl implements JobDAO {

	private EntityManager entityManager;

	@Autowired
	public JobDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Job> findAll() {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Job> theQuery = currentSession.createQuery("from Job", Job.class);

		List<Job> jobs = theQuery.getResultList();

		return jobs;
	}

	@Override
	public Job findById(int theId) {

		Session currentSession = entityManager.unwrap(Session.class);

		Job theJob = currentSession.get(Job.class, theId);

		return theJob;
	}

	@Override
	public void save(Job theJob) {

		Session currentSession = entityManager.unwrap(Session.class);
		theJob.setPublishedDate(new Timestamp(System.currentTimeMillis()));
		currentSession.saveOrUpdate(theJob);
	}

	@Override
	public void deleteById(int theId) {

		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery1 = currentSession.createQuery("delete from Application where job=:job");
		theQuery1.setParameter("job", findById(theId));
		theQuery1.executeUpdate();
		Query theQuery = currentSession.createQuery("delete from Job where id=:jobId");
		theQuery.setParameter("jobId", theId);

		theQuery.executeUpdate();
		
	}

	@Override
	public List<Job> getJobsPulishedByUser(String username) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Job> theQuery = currentSession
				.createQuery("select j from Job as j inner join j.user as u where u.username=:username", Job.class);
		theQuery.setParameter("username", username);
		List<Job> jobs = theQuery.getResultList();
		return jobs;
	}

	@Override
	public int getUserId(String username) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("select u.id from User u where username=:username");
		query.setParameter("username", username);
		int userId = (int) query.uniqueResult();
		return userId;
	}

	@Override
	public User getUser(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		User theUser = currentSession.get(User.class, theId);
		return theUser;
	}

	@Override
	public void createApllication(int userId, int jobId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Application application = new Application(getUser(userId), findById(jobId));
		currentSession.save(application);
	}

	@Override
	public void saveUser(User user) {
		Session currentSession = entityManager.unwrap(Session.class);
		user.setEnabled(true);
		user.setRole(currentSession.get(Role.class, 1));
		currentSession.saveOrUpdate(user);
	}

	@Override
	public List<Job> searchJob(String input) {

		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = null;

		if (input != null && input.trim().length() > 0) {

			theQuery = currentSession.createQuery("from Job where lower(description) like :input", Job.class);
			theQuery.setParameter("input", "%" + input.toLowerCase() + "%");

		} else {
			theQuery = currentSession.createQuery("from Job", Job.class);
		}

		List<Job> jobs = theQuery.getResultList();

		return jobs;

	}

	@Override
	public boolean hasAlreadyApplied(User user, Job job) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery =currentSession.createQuery("select 1 from Application where user=:user and job=:job");
		theQuery.setParameter("user", user);
		theQuery.setParameter("job", job);
		return (theQuery.uniqueResult() != null);
	}
}
