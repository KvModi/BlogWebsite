package com;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.*;
import com.model.*;

public class JobController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JobDao jobDao;
	
	@RequestMapping(value="/addjob", method=RequestMethod.POST)
	public ResponseEntity<?> addJob(@RequestBody Job job, HttpSession session){
	
		String email=(String)session.getAttribute("loginId");
		if(email==null) {
			ErrorClazz error=new ErrorClazz(4, "Unauthorised access");
			return new ResponseEntity<ErrorClazz> (error, HttpStatus.UNAUTHORIZED);			
		}
		
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")) {
			ErrorClazz error=new ErrorClazz(5, "Access Denied");
			return new ResponseEntity<ErrorClazz> (error, HttpStatus.UNAUTHORIZED);			
		}
		try {
			jobDao.addJob(job);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
			}
		catch(Exception e) {
			ErrorClazz error=new ErrorClazz(6, "Unable to post Job Details : "+e.getMessage());
			return new ResponseEntity<ErrorClazz> (error, HttpStatus.INTERNAL_SERVER_ERROR);			
		
		}
		
	}
	
}
