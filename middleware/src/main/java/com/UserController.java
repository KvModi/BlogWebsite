package com;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
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
	
	/*@RequestMapping(value="/home")
	public String goToHome() {
		return "redirect:home.html";
		
	}*/
	
	@RequestMapping(value="/registeruser", method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		System.out.println(user.toString());
		
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
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session){
		System.out.println(user);
		User validUser=userDao.login(user);
		System.out.println(validUser);
		if(validUser==null) {
			ErrorClazz error=new ErrorClazz(5, "Login Failed. Invalid email or password. \n TRY AGAIN");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
			}
		else
		{
			validUser.setOnline(true);
			userDao.update(validUser);
			session.setAttribute("loginId", user.getEmail());
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
		}
	}
	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		String email=(String)session.getAttribute("loginId");
		if(email==null) {
			ErrorClazz error=new ErrorClazz(4, "Please Login ");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
			
		}
		System.out.println("User controller : exit if");
		User user =userDao.getUser(email);
		System.out.println(email+ "  and user is "+user);
		user.setOnline(false);
		System.out.println("User controller : /logout : 		user.setOnline(false);");
		userDao.update(user);
		System.out.println("User controller : /logout :userDao.update(user); ");
		session.removeAttribute("loginId");
		System.out.println("User controller : /logout :session.removeAttribute(\"loginId\"); ");
		session.invalidate();
		System.out.println("User controller : /logout :session.invalidate(); ");
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	@RequestMapping(value="/getuser", method=RequestMethod.GET)
	public ResponseEntity<?> getUser (HttpSession session){
		String email=(String )session.getAttribute("loginId");
		if(email==null) {
			ErrorClazz error=new ErrorClazz(5, "UNAUTHORIZED access. ");
			System.out.println("UserController: getUser:UNAUTHORIZED access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
			
		}
		User user=userDao.getUser(email);
		System.out.println("UserController: getUser:User user=userDao.getUser(email);");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/updateuser", method=RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User user,HttpSession session){
		String email=(String )session.getAttribute("loginId");
		if(email==null) {
			ErrorClazz error=new ErrorClazz(4, "Please Login ");
			System.out.println("UserController: getUser:Login error");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		try {
			userDao.update(user);
			return new ResponseEntity<User>(user,HttpStatus.OK);}
		catch(Exception e) {
			ErrorClazz error= new ErrorClazz(5, "Unable to update the details");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
