package com.dao;

import com.model.User;

public interface UserDao {
	void registerUser(User user);
	boolean isEmailUnique(String email);

}
