package com.taskManager.taskManagerService.repository;

import org.junit.Assert;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
@TestPropertySource("classpath:application-unit-test.properties")
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
    @Before
    public void setup() {
    	userRepository.deleteAll();
    }
    
	@Test
	public void testSaveAndFindUserDetails() {	  
	  User user = new User();
	  user.setEmployeeId(111);
	  user.setFirstName("test");
	  user.setLastName("test");
	  User savedUser = userRepository.save(user);
	  
	  User usr = userRepository.findOneByUserId(savedUser.getUserId());
	  
	  Assert.assertEquals(111, usr.getEmployeeId());
	  Assert.assertEquals("test", usr.getFirstName());
	  Assert.assertEquals("test", usr.getLastName());
	}
	
	@Test
	public void testFindOneByProjectId() {	  
	  User user = new User();
	  user.setEmployeeId(111);
	  user.setFirstName("test");
	  user.setLastName("test");
	  user.setProjectId(1);
	  userRepository.save(user);
	  
	  
	  User usr = userRepository.findOneByProjectId(1);
	  
	  Assert.assertEquals(111, usr.getEmployeeId());
	  Assert.assertEquals("test", usr.getFirstName());
	  Assert.assertEquals("test", usr.getLastName());
	}

}
