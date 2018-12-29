package com.taskManager.taskManagerService.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taskManager.taskManagerService.Environment;
import com.taskManager.taskManagerService.domain.Parent;
import com.taskManager.taskManagerService.repository.ParentRepository;
import com.taskManager.taskManagerService.service.impl.ParentTaskServiceImpl;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
@TestPropertySource("classpath:application-unit-test.properties")
public class ParentServiceImplIntegratedTest {
	
	@Autowired
	private ParentTaskServiceImpl parentTaskServiceImpl;
	
	@Autowired
	private ParentRepository parentRepository;
    
	@Before
	public void setup() {
		parentRepository.deleteAll();
	}
	
	@Test
	public void testAddParentTask() throws Exception {
	  Parent parent = new Parent();
	  parent.setParentTask("parent");
	  parentTaskServiceImpl.addParentTask(parent);
	}
	
	@Test
	public void testGetAllParentTasks() throws Exception {
	  Parent parent = new Parent();
	  parent.setParentTask("parent");
	  parentRepository.save(parent);
	  List<Parent> list = (List<Parent>) parentTaskServiceImpl.getAllParentTasks();
	  assertEquals("parent", list.get(0).getParentTask());
	}
	

}
