package com.taskManager.taskManagerService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taskManager.taskManagerService.Environment;
import com.taskManager.taskManagerService.domain.User;
import com.taskManager.taskManagerService.repository.UserRepository;
import com.taskManager.taskManagerService.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
@TestPropertySource("classpath:application-unit-test.properties")
public class UserServiceImplIntegratedTest {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserRepository userRepository;
    
	@Before
	public void setup() {
		userRepository.deleteAll();
	}
	
	@Test
	public void testAddUser() throws Exception {
	  User user = new User();
	  user.setFirstName("test");
	  user.setLastName("test");
	  user.setEmployeeId(111);
	  userServiceImpl.addUser(user);
	}
	
	@Test
	public void testGetAllUsers() throws Exception {
	  User user = new User();
	  user.setFirstName("test");
	  user.setLastName("test");
	  user.setEmployeeId(111);
	  userRepository.save(user);
	  List<User> list = (List<User>) userServiceImpl.getAllUsers();
	  assertEquals("test", list.get(0).getFirstName());
	}
	
	@Test
	public void testUpdateUserDetails() throws Exception {
	  User user = new User();
	  user.setFirstName("test");
	  user.setLastName("test");
	  user.setEmployeeId(111);
	  User saved = userRepository.save(user);
	  User user1 = new User();
	  user1.setFirstName("test updated");
	  user1.setLastName("test");
	  user1.setUserId(saved.getUserId());
	  user1.setEmployeeId(111);
	  userServiceImpl.updateUserDetails(user1, saved.getUserId());
	  User usr = userRepository.findOneByUserId(saved.getUserId());
	  assertNotNull(usr);
	  assertEquals("test updated", usr.getFirstName());
	}
	
	@Test
	public void testDeleteUserDetails() throws Exception {
	  User user = new User();
	  user.setFirstName("test");
	  user.setLastName("test");
	  user.setEmployeeId(111);
	  User saved = userRepository.save(user);
	 
	  userServiceImpl.deleteUserDetails(saved.getUserId());
	  User usr = userRepository.findOneByUserId(saved.getUserId());
	  assertNull(usr);
	}
	

}
