package com.shrmus.service;

import com.shrmus.pojo.User;

public interface UserService {

	void addUser(User user);

	User getUserByUsername(String username);

	void updateUser(User user);
	
	void deleteUser(Integer userId);
}
