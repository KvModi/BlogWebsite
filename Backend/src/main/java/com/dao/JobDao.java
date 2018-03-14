package com.dao;
import java.util.List;

import com.model.Job;

public interface JobDao {
	void addJob(Job job);
	List<Job> getAllJobs();
	Job getJob(int id);
}
