package com.taskManager.taskManagerService.controller;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.taskManager.taskManagerService.domain.Parent;
import com.taskManager.taskManagerService.domain.Project;
import com.taskManager.taskManagerService.domain.TaskManager;
import com.taskManager.taskManagerService.domain.User;
import com.taskManager.taskManagerService.service.impl.ParentTaskServiceImpl;
import com.taskManager.taskManagerService.service.impl.ProjectServiceImpl;
import com.taskManager.taskManagerService.service.impl.TaskManagerServiceImpl;
import com.taskManager.taskManagerService.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
public class ProjectManagerControllerTest {
	@InjectMocks
	private ProjectManagerController taskManagerController;

	@Mock
	private TaskManagerServiceImpl taskManagerService;
	
	@Mock
	private UserServiceImpl userService;
	
	@Mock
	private ProjectServiceImpl projectService;
	
	@Mock
	private ParentTaskServiceImpl parentService;

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
           task.setTask("running");
           task.setPriority(1);
           task.setTaskId(1);
           task.setParentId(1);
		   String json = mapper.writeValueAsString(task);
		    mvc.perform(post("/v1/update/taskmanager/"+1)
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk());
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
		       .andExpect(status().isNotFound());
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
		       .andExpect(status().isOk());
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
	
	//user
	@Test
	public void whenAddUserIsCalledThenReturnSuccess() throws Exception {
           User user = new User();
           user.setFirstName("test");
           user.setEmployeeId(111);
           user.setLastName("test");
		   String json = mapper.writeValueAsString(user);
		    mvc.perform(post("/v1/add/users")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isCreated());
	}
	
	@Test
	public void whenAddUserIsCalledAndNoValidJSONThenReturnSuccess() throws Exception {
           User user = null;
		   String json = mapper.writeValueAsString(user);
		    mvc.perform(post("/v1/add/users")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenRetrieveUserIsCalledThenReturnSuccess() throws Exception {
           mvc.perform(get("/v1/retrieve/users"))
		       .andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void whenRetrieveUserIsCalledAndServiceIsDownThenReturnException() throws Exception {
        Mockito.when(userService.getAllUsers()).thenThrow(new RuntimeException());   
		mvc.perform(get("/v1/retrieve/users"))
		       .andExpect(status().isInternalServerError());
	}
	
	@Test
	public void whenUpdateUserIsCalledThenReturnSuccess() throws Exception {
           User user = new User();
           user.setFirstName("test");
           user.setEmployeeId(111);
           user.setLastName("test");
		   String json = mapper.writeValueAsString(user);
		    mvc.perform(post("/v1/update/user/"+1)
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk());
	}
	
	@Test
	public void whenUpdateUserIsCalledAndNoValidJSONThenReturnSuccess() throws Exception {
           User user = null;
		   String json = mapper.writeValueAsString(user);
	       mvc.perform(post("/v1/update/user/"+1)
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenDeleteUserIsCalledThenReturnSuccess() throws Exception {
          
		    mvc.perform(delete("/v1/delete/user/"+1))
		       .andExpect(status().isOk());
	}
	
	@Test
	public void whenDeleteUserIsCalledWithNoUserIdThenReturnSuccess() throws Exception {
          
		    mvc.perform(delete("/v1/delete/user/"))
		       .andExpect(status().isNotFound());
	}
	
	@Test
	public void whenDeleteUserIsCalledAndServiceIsDownThenReturnException() throws Exception {
		doThrow(new RuntimeException("Service is down!")).when(userService).deleteUserDetails(Mockito.any());
		mvc.perform(delete("/v1/delete/user/"+1))
		       .andExpect(status().isInternalServerError());
	}
	//project
	@Test
	public void whenAddProjectIsCalledThenReturnSuccess() throws Exception {
           Project project = new Project();
           project.setProject("project");
		   String json = mapper.writeValueAsString(project);
		    mvc.perform(post("/v1/add/projects")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isCreated());
	}
	
	@Test
	public void whenAddProjectIsCalledAndNoValidJSONThenReturnSuccess() throws Exception {
		   Project project = null;
		   String json = mapper.writeValueAsString(project);
		    mvc.perform(post("/v1/add/projects")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenUpdateProjectIsCalledThenReturnSuccess() throws Exception {
			Project project = new Project();
        	project.setProject("project");
		   String json = mapper.writeValueAsString(project);
		    mvc.perform(post("/v1/update/projects/"+1)
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk());
	}
	
	@Test
	public void whenUpdateProjectIsCalledAndNoValidIDThenReturnSuccess() throws Exception {
			Project project = new Project();
        	project.setProject("project");
		   String json = mapper.writeValueAsString(project);
		    mvc.perform(post("/v1/update/projects/")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isNotFound());
	}
	
	@Test
	public void whenUpdateProjectIsCalledAndNoValidJSONThenReturnSuccess() throws Exception {
		   Project project = null;
		   String json = mapper.writeValueAsString(project);
	       mvc.perform(post("/v1/update/projects/"+1)
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenRetrieveProjectIsCalledThenReturnSuccess() throws Exception {
           mvc.perform(get("/v1/retrieve/projects"))
		       .andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void whenRetrieveProjectIsCalledAndServiceIsDownThenReturnException() throws Exception {
        Mockito.when(projectService.getAllProjects()).thenThrow(new RuntimeException());   
		mvc.perform(get("/v1/retrieve/projects"))
		       .andExpect(status().isInternalServerError());
	}
	
	//parent
	@Test
	public void whenAddParentIsCalledThenReturnSuccess() throws Exception {
           Parent parent = new Parent();
           parent.setParentTask("parentTask");
		   String json = mapper.writeValueAsString(parent);
		    mvc.perform(post("/v1/add/parentTask")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isCreated());
	}
	
	@Test
	public void whenAddParentIsCalledAndNoValidJSONThenReturnSuccess() throws Exception {
		   Parent parent = null;
		   String json = mapper.writeValueAsString(parent);
		    mvc.perform(post("/v1/add/parentTask")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isBadRequest());
	}
	
	
	
	@Test
	public void whenRetrieveParentIsCalledThenReturnSuccess() throws Exception {
           mvc.perform(get("/v1/retrieve/parentTasks"))
		       .andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void whenRetrieveParentIsCalledAndServiceIsDownThenReturnException() throws Exception {
        Mockito.when(parentService.getAllParentTasks()).thenThrow(new RuntimeException());   
		mvc.perform(get("/v1/retrieve/parentTasks"))
		       .andExpect(status().isInternalServerError());
	}

}
