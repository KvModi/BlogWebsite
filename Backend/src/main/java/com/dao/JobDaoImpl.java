package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Job;

@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addJob(Job job) {
		Session session = sessionFactory.getCurrentSession();//openSession();
		session.save(job);
	}

	@SuppressWarnings("unchecked")
	public List<Job> getAllJobs() {
		Session session = sessionFactory.getCurrentSession();//openSession();
		Query query = session.createQuery("from Job");
		return query.list();
	}

	public Job getJob(int id) {
		Session session=sessionFactory.getCurrentSession();//openSession();
		Job job=(Job)session.get(Job.class, id);
		return job;
	}

}
