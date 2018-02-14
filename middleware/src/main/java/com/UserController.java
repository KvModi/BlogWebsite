package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.UserDao;
import com.model.ErrorClazz;
import com.model.User;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;
	
	public UserController()
	{
		System.out.println("UserController bean is created");
	}
	
	@RequestMapping(value="/registeruser", method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		if(!userDao.isEmailUnique(user.getEmail())) {
			ErrorClazz error=new ErrorClazz(1,"email already exists. Try to register with different email id!!");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
		}
		try {
		userDao.registerUser(user);
		}
		catch(Exception e) {
			ErrorClazz error=new ErrorClazz(2,"Please enter all the details."+e.getMessage());
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
}
