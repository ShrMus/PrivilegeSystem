package com.shrmus.service;

import java.util.List;

import com.shrmus.pojo.User;

public interface UserService {

	void addUser(User user);

	User getUserByUsername(String username);

	void updateUser(User user);
	
	void deleteUser(Integer userId);

	List<User> getUserList();

	User getUserById(Integer userId);

}
