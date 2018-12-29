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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taskManager.taskManagerService.Environment;
import com.taskManager.taskManagerService.domain.Parent;
import com.taskManager.taskManagerService.repository.ParentRepository;
import com.taskManager.taskManagerService.service.impl.ParentTaskServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
@TestPropertySource("classpath:application-unit-test.properties")
public class ParentServiceImplTest {
	
	@InjectMocks
	private ParentTaskServiceImpl parentTaskServiceImpl;
	
	@Mock
	private ParentRepository parentRepository;
    
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddParentTask() throws Exception {
	  Parent parent = new Parent();
	  parent.setParentTask("parent");
	  Mockito.when(parentRepository.save(Mockito.any())).thenReturn(parent);
	  parentTaskServiceImpl.addParentTask(parent);
	  Mockito.verify(parentRepository, Mockito.times(1)).save(Mockito.any());
	}
	
	@Test
	public void testGetAllParentTasks() throws Exception {
	  List<Parent> parentList = new ArrayList<>();
	  Parent parent = new Parent();
	  parent.setParentTask("parent");
	  parentList.add(parent);
	  Mockito.when(parentRepository.findAll()).thenReturn(parentList);
	  parentTaskServiceImpl.getAllParentTasks();
	  Mockito.verify(parentRepository, Mockito.times(1)).findAll();
	  assertEquals("parent", parentList.get(0).getParentTask());
	}

}
