package com.taskManager.taskManagerService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taskManager.taskManagerService.Environment;
import com.taskManager.taskManagerService.Exception.BadRequestException;
import com.taskManager.taskManagerService.domain.TaskManager;
import com.taskManager.taskManagerService.repository.TaskManagerRepository;
import com.taskManager.taskManagerService.service.impl.TaskManagerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
@TestPropertySource("classpath:application-unit-test.properties")
public class TaskManagerServiceImplIntegratedTest {
	
	@Autowired
	private TaskManagerServiceImpl taskManagerServiceImpl;
	
	@Autowired
	private TaskManagerRepository taskManagerRepository;
    
	@Before
	public void setup() {
		taskManagerRepository.deleteAll();
	}
	
	@Test
	public void testAddTask() throws Exception {
	  TaskManager task = new TaskManager();
	  task.setTask("task");
	  task.setStartDate(new Date(System.currentTimeMillis()));
	  task.setProjectId(1);
	  taskManagerServiceImpl.addTask(task);
	}
	
	
	@Test
	public void testUpdateTask() throws Exception {
	  TaskManager task = new TaskManager();
	  task.setTask("task");
	  task.setStartDate(new Date(System.currentTimeMillis()));
	  task.setProjectId(1);
	  TaskManager saved = taskManagerRepository.save(task);
	  TaskManager task1 = new TaskManager();
	  task1.setTask("task1");
	  task1.setStartDate(new Date(System.currentTimeMillis()));
	  task1.setProjectId(1);
	  task1.setTaskId(saved.getTaskId());
	  taskManagerServiceImpl.updateTask(task1, saved.getTaskId(), false);
	  TaskManager updatedTask = taskManagerRepository.findOneByTaskId(saved.getTaskId());
	  assertNotNull(updatedTask);
	  assertEquals("task1", updatedTask.getTask());
	}
	
	@Test(expected=BadRequestException.class)
	public void testUpdateTaskWithNoExistingTaskManager() throws Exception {
	  TaskManager task = new TaskManager();
	  task.setTask("task");
	  task.setStartDate(new Date(System.currentTimeMillis()));
	  task.setProjectId(1);
	  TaskManager task1 = new TaskManager();
	  task1.setTask("task1");
	  task1.setStartDate(new Date(System.currentTimeMillis()));
	  task1.setProjectId(1);
	  task1.setTaskId(1);
	  taskManagerServiceImpl.updateTask(task1, 1, false);
	  
	}
	
	
	@Test(expected=BadRequestException.class)
	public void testUpdateTaskWithNoTaskManager() throws Exception {	 
	  taskManagerServiceImpl.updateTask(null, 1, false);	  
	}
	
	@Test(expected=BadRequestException.class)
	public void testUpdateTaskWithNoTaskId() throws Exception {	 
	  TaskManager task = new TaskManager();
	  task.setTask("task");
	  task.setStartDate(new Date(System.currentTimeMillis()));
	  task.setProjectId(1);
	  TaskManager saved = taskManagerRepository.save(task);
	  taskManagerServiceImpl.updateTask(task, 0, false);	  
	}
	
	@Test(expected=BadRequestException.class)
	public void testUpdateTaskWithNoMatchigTaskId() throws Exception {
	  TaskManager task = new TaskManager();
	  task.setTask("task");
	  task.setStartDate(new Date(System.currentTimeMillis()));
	  task.setProjectId(1);
	  TaskManager saved = taskManagerRepository.save(task);
	  TaskManager task1 = new TaskManager();
	  task1.setTask("task1");
	  task1.setStartDate(new Date(System.currentTimeMillis()));
	  task1.setProjectId(1);
	  task1.setTaskId(saved.getTaskId());
	  taskManagerServiceImpl.updateTask(task1, 1, false);
	
	}
	
	@Test
	public void testEndTask() throws Exception {
	  TaskManager task = new TaskManager();
	  task.setTask("task");
	  task.setStartDate(new Date(System.currentTimeMillis()));
	  task.setProjectId(1);
	  TaskManager saved = taskManagerRepository.save(task);
	  TaskManager task1 = new TaskManager();
	  task1.setTask("task");
	  task1.setStartDate(new Date(System.currentTimeMillis()));
	  task1.setProjectId(1);
	  task1.setTaskId(saved.getTaskId());
	  task1.setEndDate(new Date(System.currentTimeMillis()));
	  taskManagerServiceImpl.updateTask(task1, saved.getTaskId(), true);
	  TaskManager updatedTask = taskManagerRepository.findOneByTaskId(saved.getTaskId());
	  assertNotNull(updatedTask);
	  assertNotNull(updatedTask.getEndDate());
	}
	

}
