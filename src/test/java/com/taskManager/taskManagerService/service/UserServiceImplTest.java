package com.taskManager.taskManagerService.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
public class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository;
    
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddUser() throws Exception {
	  User user = new User();
	  user.setFirstName("test");
	  user.setLastName("test");
	  user.setEmployeeId(111);
	  Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
	  userServiceImpl.addUser(user);
	  Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
	}
	
	@Test
	public void testGetAllUsers() throws Exception {
	  List<User> userList = new ArrayList<>();
	  User user1 = new User();
	  user1.setFirstName("test");
	  user1.setLastName("test");
	  user1.setEmployeeId(111);
	  user1.setUserId(1);
	  userList.add(user1);
	  Mockito.when(userRepository.findAll()).thenReturn(userList);
	  userServiceImpl.getAllUsers();
	  Mockito.verify(userRepository, Mockito.times(1)).findAll();
	}

}
