package com.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory ;
	public void registerUser(User user) {
		Session session= sessionFactory.getCurrentSession();
		session.save(user);

	}
	public boolean isEmailUnique(String email) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, email);
		if(user==null)
			return true;
		else
		return false;
	}

}
