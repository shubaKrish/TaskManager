package com.taskManager.taskManagerService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskManager.taskManagerService.Exception.BadRequestException;
import com.taskManager.taskManagerService.domain.User;
import com.taskManager.taskManagerService.repository.UserRepository;
import com.taskManager.taskManagerService.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
	@Override
	public void addUser(User user) throws Exception {
		userRepository.save(user);
	}
	
	@Override
	public Iterable<User> getAllUsers() throws Exception {
		return userRepository.findAll();

	}
	
	@Override
	public void updateUserDetails(User user, Integer userId) throws Exception {
		User existingUser = userRepository.findOneByUserId(userId);
		if(existingUser==null) {
			throw new BadRequestException("User not found in Database to update");
		} else if(existingUser.getUserId()!=user.getUserId() || userId!=user.getUserId()){
			throw new BadRequestException("User id is not matching!");

		} else {
			existingUser.setEmployeeId(user.getEmployeeId());
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			userRepository.save(existingUser);
		}
		
	}
	
	@Override
	public void deleteUserDetails(Integer userId) throws Exception {
		User existingUser = userRepository.findOneByUserId(userId);
		if(existingUser==null) {
			throw new BadRequestException("No user in Database to delete");
		}
		userRepository.delete(existingUser);
	}
	
}
