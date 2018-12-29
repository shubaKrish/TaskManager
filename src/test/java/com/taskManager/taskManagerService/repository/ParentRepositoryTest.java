package com.taskManager.taskManagerService.repository;

import java.util.List;

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
import com.taskManager.taskManagerService.domain.Parent;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
@TestPropertySource("classpath:application-unit-test.properties")
public class ParentRepositoryTest {
	
	@Autowired
	private ParentRepository parentRepository;
	
    @Before
    public void setup() {
    	parentRepository.deleteAll();
    }
    
	@Test
	public void testFindAllParentDetails() {	  
	  Parent parentTask  = null;
	  Parent parent = new Parent();
	  parent.setParentTask("parent");
	  Parent savedParent =parentRepository.save(parent);
	  
	  List<Parent> parentList = (List<Parent>) parentRepository.findAll();
	 
	  Assert.assertEquals(savedParent.getParentId(), parentList.get(0).getParentId());
	  Assert.assertEquals("parent", parentList.get(0).getParentTask());
	}

}
