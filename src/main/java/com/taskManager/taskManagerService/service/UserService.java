package com.taskManager.taskManagerService.service;

import com.taskManager.taskManagerService.domain.User;

public interface UserService {
	public void addUser(User user) throws Exception;
	public Iterable<User> getAllUsers() throws Exception;
	public void updateUserDetails(User user,Integer id) throws Exception;
	public void deleteUserDetails(Integer userId) throws Exception;
}
