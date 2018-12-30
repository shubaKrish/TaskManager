package com.taskManager.taskManagerService.repository;

import java.sql.Date;

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
import com.taskManager.taskManagerService.domain.TaskManager;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
@TestPropertySource("classpath:application-unit-test.properties")
public class TaskManagerRepositoryTest {
	
	@Autowired
	private TaskManagerRepository taskManagerRepository;
	
	
    @Before
    public void setup() {
    	taskManagerRepository.deleteAll();
    }
    
	@Test
	public void testSaveAndFindTaskDetails() {	  
	  TaskManager task = new TaskManager();
	  task.setTask("test task");
	  task.setPriority(1);
	  task.setProjectId(1);
	  task.setStartDate(new Date(System.currentTimeMillis()));
	  TaskManager savedTask = taskManagerRepository.save(task);	
	  
	  TaskManager availableTask = taskManagerRepository.findOneByTaskId(savedTask.getTaskId());
	  Assert.assertEquals("test task", availableTask.getTask());
	  Assert.assertEquals(1, availableTask.getPriority());
	}
	
	

}
