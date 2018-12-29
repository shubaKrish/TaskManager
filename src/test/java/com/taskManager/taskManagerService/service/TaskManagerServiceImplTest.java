package com.taskManager.taskManagerService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
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
import com.taskManager.taskManagerService.domain.TaskManager;
import com.taskManager.taskManagerService.repository.TaskManagerRepository;
import com.taskManager.taskManagerService.service.impl.TaskManagerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
@TestPropertySource("classpath:application-unit-test.properties")
public class TaskManagerServiceImplTest {
	
	@InjectMocks
	private TaskManagerServiceImpl taskManagerServiceImpl;
	
	@Mock
	private TaskManagerRepository taskManagerRepository;
    
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddParentTask() throws Exception {
	  TaskManager task = new TaskManager();
	  task.setTask("task");
	  task.setStartDate(new Date(System.currentTimeMillis()));
	  task.setProjectId(1);
	  Mockito.when(taskManagerRepository.save(Mockito.any())).thenReturn(task);
	  taskManagerServiceImpl.addTask(task);
	  Mockito.verify(taskManagerRepository, Mockito.times(1)).save(Mockito.any());
	}	
	

}
