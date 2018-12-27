package com.taskManager.taskManagerService.controller;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskManager.taskManagerService.Environment;
import com.taskManager.taskManagerService.domain.TaskManager;
import com.taskManager.taskManagerService.service.impl.TaskManagerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
public class TaskManagerControllerTest {
	@InjectMocks
	private TaskManagerController taskManagerController;

	@Mock
	private TaskManagerServiceImpl taskManagerService;

	private MockMvc mvc;
	
	 @Autowired 
	 private ObjectMapper mapper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(taskManagerController).build();
	}

	@Test
	public void whenRetrieveTaskManagerIsCalledThenReturnSuccess() throws Exception {

		mvc.perform(get("/v1/retrieve/taskmanager")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void whenRetrieveTaskManagerIsCalledWithServiceErrorThenReturnException() throws Exception {
		Mockito.when(taskManagerService.getAllTask()).thenThrow(new RuntimeException());
		mvc.perform(get("/v1/retrieve/taskmanager")).andExpect(status().isInternalServerError());
	}

	@Test
	public void whenAddTaskManagerIsCalledThenReturnSuccess() throws Exception {
           TaskManager task = new TaskManager();
           task.setEndDate(new Date(System.currentTimeMillis()));
           task.setStartDate(new Date(System.currentTimeMillis()));
         //  task.setParentTask("workout");
           task.setTask("running");
           task.setPriority(1);
           task.setTaskId(1);
           task.setParentId(1);
		   String json = mapper.writeValueAsString(task);
		    mvc.perform(post("/v1/add/taskmanager")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isCreated());
	}

	@Test
	public void whenAddTaskManagerIsCalledWithServiceErrorThenReturnException() throws Exception {
		TaskManager task = new TaskManager();
        task.setEndDate(new Date(System.currentTimeMillis()));
        task.setStartDate(new Date(System.currentTimeMillis()));
       // task.setParentTask("workout");
        task.setTask("running");
        task.setPriority(1);
        task.setTaskId(1);
        task.setParentId(1);
		   String json = mapper.writeValueAsString(task);
		   doThrow(new RuntimeException()).when(taskManagerService).addTask(Mockito.any());;
		mvc.perform(post("/v1/add/taskmanager")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(json)
			       .accept(MediaType.APPLICATION_JSON))
			       .andExpect(status().isInternalServerError());
	}
	
	@Test
	public void whenAddTaskManagerIsCalledWithNoValidJSONThenReturnBadRequest() throws Exception {
		TaskManager task = null;
		   String json = mapper.writeValueAsString(task);
		   mvc.perform(post("/v1/add/taskmanager")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(json)
			       .accept(MediaType.APPLICATION_JSON))
			       .andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenUpdateTaskManagerIsCalledAndValidJSONThenReturnSuccess() throws Exception {
           TaskManager task = new TaskManager();
           task.setEndDate(new Date(System.currentTimeMillis()));
           task.setStartDate(new Date(System.currentTimeMillis()));
         //  task.setParentTask("workout");
           task.setTask("running");
           task.setPriority(1);
           task.setTaskId(1);
           task.setParentId(1);
		   String json = mapper.writeValueAsString(task);
		    mvc.perform(post("/v1/update/taskmanager/"+1)
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isCreated());
	}

	@Test
	public void whenUpdateTaskManagerIsCalledWithServiceErrorThenReturnException() throws Exception {
		TaskManager task = new TaskManager();
        task.setEndDate(new Date(System.currentTimeMillis()));
        task.setStartDate(new Date(System.currentTimeMillis()));
        //task.setParentTask("workout");
        task.setTask("running");
        task.setPriority(1);
        task.setTaskId(1);
        task.setParentId(1);
		   String json = mapper.writeValueAsString(task);
		   doThrow(new RuntimeException()).when(taskManagerService).updateTask(Mockito.any(),Mockito.any(),Mockito.any());;
		mvc.perform(post("/v1/update/taskmanager/"+1)
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(json)
			       .accept(MediaType.APPLICATION_JSON))
			       .andExpect(status().isInternalServerError());
	}
	
	@Test
	public void whenUpdateTaskManagerIsCalledWithNoValidJSONThenReturnBadRequest() throws Exception {
		TaskManager task = null;
		   String json = mapper.writeValueAsString(task);
		mvc.perform(post("/v1/update/taskmanager/"+1)
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(json)
			       .accept(MediaType.APPLICATION_JSON))
			       .andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenUpdateTaskManagerIsCalledAndNoTaskIdThenReturnBadRequest() throws Exception {
           TaskManager task = new TaskManager();
           task.setEndDate(new Date(System.currentTimeMillis()));
           task.setStartDate(new Date(System.currentTimeMillis()));
        //   task.setParentTask("workout");
           task.setTask("running");
           task.setPriority(1);
           task.setTaskId(1);
           task.setParentId(1);
		   String json = mapper.writeValueAsString(task);
		    mvc.perform(post("/v1/update/taskmanager/")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenEndTaskManagerIsCalledAndValidJSONThenReturnSuccess() throws Exception {
           TaskManager task = new TaskManager();
           task.setEndDate(new Date(System.currentTimeMillis()));
           task.setStartDate(new Date(System.currentTimeMillis()));
      //     task.setParentTask("workout");
           task.setTask("running");
           task.setPriority(1);
           task.setTaskId(1);
           task.setParentId(1);
		   String json = mapper.writeValueAsString(task);
		    mvc.perform(post("/v1/update/taskmanager/"+1+"/endTask")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isCreated());
	}

	@Test
	public void whenEndTaskManagerIsCalledWithServiceErrorThenReturnException() throws Exception {
		TaskManager task = new TaskManager();
        task.setEndDate(new Date(System.currentTimeMillis()));
        task.setStartDate(new Date(System.currentTimeMillis()));
       // task.setParentTask("workout");
        task.setTask("running");
        task.setPriority(1);
        task.setTaskId(1);
        task.setParentId(1);
		   String json = mapper.writeValueAsString(task);
		   doThrow(new RuntimeException()).when(taskManagerService).updateTask(Mockito.any(),Mockito.any(),Mockito.any());;
		mvc.perform(post("/v1/update/taskmanager/"+1+"/endTask")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(json)
			       .accept(MediaType.APPLICATION_JSON))
			       .andExpect(status().isInternalServerError());
	}
	
	@Test
	public void whenEndTaskManagerIsCalledWithNoValidJSONThenReturnBadRequest() throws Exception {
		TaskManager task = null;
		   String json = mapper.writeValueAsString(task);
		mvc.perform(post("/v1/update/taskmanager/"+1+"/endTask")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(json)
			       .accept(MediaType.APPLICATION_JSON))
			       .andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenEndTaskManagerIsCalledAndNoTaskIdThenReturnBadRequest() throws Exception {
           TaskManager task = new TaskManager();
           task.setEndDate(new Date(System.currentTimeMillis()));
           task.setStartDate(new Date(System.currentTimeMillis()));
           //task.setParentTask("workout");
           task.setTask("running");
           task.setPriority(1);
           task.setTaskId(1);
           task.setParentId(1);
		   String json = mapper.writeValueAsString(task);
		    mvc.perform(post("/v1/update/taskmanager//endTask")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isBadRequest());
	}

}
