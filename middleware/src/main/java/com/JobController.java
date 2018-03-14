package com;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.JobDao;
import com.dao.UserDao;
import com.model.ErrorClazz;
import com.model.Job;
import com.model.User;

@Controller
@ComponentScan("com.*")
public class JobController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JobDao jobDao;
	
	
	@RequestMapping(value="/addjob", method=RequestMethod.POST)
	public ResponseEntity<?> addJob(@RequestBody Job job, HttpSession session){
	
		/*String email="admin@gmail.com";
*/		String email=(String)session.getAttribute("loginId");
		if(email==null) {
			ErrorClazz error=new ErrorClazz(4, "Unauthorised access");
			return new ResponseEntity<ErrorClazz> (error, HttpStatus.UNAUTHORIZED);			
		}
		
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")) {
			System.out.println("user is not admin");
			ErrorClazz error=new ErrorClazz(5, "Access Denied");
			return new ResponseEntity<ErrorClazz> (error, HttpStatus.UNAUTHORIZED);			
		}
		try {
			System.out.println("JobController : try to add job:");
			job.setPostedOn(new Date());
			jobDao.addJob(job);
			System.out.println("JobController :  added job:");
			return new ResponseEntity<Job>(job, HttpStatus.OK);
			}
		catch(Exception e) {
			System.out.println("JobController : Entered catch to add job. job not added");
			ErrorClazz error=new ErrorClazz(6, "Unable to post Job Details : "+e.getMessage());
			return new ResponseEntity<ErrorClazz> (error, HttpStatus.INTERNAL_SERVER_ERROR);			
		
		}
		
	}
	@RequestMapping(value="/alljobs", method= RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		String email=(String)session.getAttribute("loginId");
		if(email==null) {
			ErrorClazz error=new ErrorClazz(4, "Unauthorised access");
			return new ResponseEntity<ErrorClazz> (error, HttpStatus.UNAUTHORIZED);			
		}
		
		List<Job> jobs=jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getjob/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> getJob(@PathVariable int id, HttpSession session){
		return null;
	//TODO complete this block. its empty ryt now
		
		
	}
	
	
}
