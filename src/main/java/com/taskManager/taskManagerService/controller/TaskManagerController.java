package com.taskManager.taskManagerService.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.taskManagerService.Exception.BadRequestException;
import com.taskManager.taskManagerService.domain.Parent;
import com.taskManager.taskManagerService.domain.Project;
import com.taskManager.taskManagerService.domain.TaskManager;
import com.taskManager.taskManagerService.domain.User;
import com.taskManager.taskManagerService.service.impl.ParentTaskServiceImpl;
import com.taskManager.taskManagerService.service.impl.ProjectServiceImpl;
import com.taskManager.taskManagerService.service.impl.TaskManagerServiceImpl;
import com.taskManager.taskManagerService.service.impl.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "taskmanager")
public class TaskManagerController {

	@Autowired
	@Resource(name = "taskManagerService")
	private TaskManagerServiceImpl taskManagerService;
	
	@Autowired
	@Resource(name = "userService")
	private UserServiceImpl userService;
	
	@Autowired
	@Resource(name = "projectService")
	private ProjectServiceImpl projectService;
	
	@Autowired
	@Resource(name = "parentTaskService")
	private ParentTaskServiceImpl parentTaskService;

	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/retrieve/taskmanager", method = RequestMethod.GET, produces = "application/JSON")
	@ApiOperation(value = "View a list of available task", response = Iterable.class)
	public ResponseEntity<Iterable<TaskManager>> retrieveTaskManager() {
		
		Iterable<TaskManager> listOfTask = null;
		try {
			listOfTask = taskManagerService.getAllTask();
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in retrieveTaskManager:::" + ex);
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in retrieveTaskManager:::" + ex);
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(listOfTask, HttpStatus.OK);

	}	
	
	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/add/taskmanager", method = RequestMethod.POST, consumes = "application/JSON")
	@ApiOperation(value = "create a new task", response = String.class)
	public ResponseEntity<String> saveTaskManager(@RequestBody TaskManager task) {
		try {
			taskManagerService.addTask(task);
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in saveTaskManager:::" + ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in saveTaskManager:::" + ex);
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/update/taskmanager/{taskId}", method = RequestMethod.POST, consumes = "application/JSON")
	@ApiOperation(value = "update a new task", response = String.class)
	public ResponseEntity<String> updateTaskManager(@PathVariable Integer taskId, @RequestBody TaskManager task) {
		try {
			taskManagerService.updateTask(task, taskId, false);
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in updateTaskManager:::" + ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in updateTaskManager:::" + ex);
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/update/taskmanager/{taskId}/endTask", method = RequestMethod.POST, consumes = "application/JSON")
	@ApiOperation(value = "update a end task", response = String.class)
	public ResponseEntity<String> endTaskManager(@PathVariable Integer taskId, @RequestBody TaskManager task) {
		try {
			taskManagerService.updateTask(task, taskId, true);
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in endTaskManager:::" + ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in endTaskManager:::" + ex);
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/add/users", method = RequestMethod.POST, consumes = "application/JSON")
	@ApiOperation(value = "create a user", response = String.class)
	public ResponseEntity<String> saveUserDetails(@RequestBody User user) {
		try {
			userService.addUser(user);
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in saveTaskManager:::" + ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in saveTaskManager:::" + ex);
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/retrieve/users", method = RequestMethod.GET, produces = "application/JSON")
	@ApiOperation(value = "View a list of users", response = Iterable.class)
	public ResponseEntity<Iterable<User>> retrieveUsers() {
		
		Iterable<User> users = null;
		try {
			users = userService.getAllUsers();
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in retrieveUsers:::" + ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in retrieveUsers:::" + ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);

	}	
	
	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/update/user/{userId}", method = RequestMethod.POST, consumes = "application/JSON")
	@ApiOperation(value = "update a user", response = String.class)
	public ResponseEntity<String> updateUserDetails(@PathVariable Integer userId, @RequestBody User user) {
		try {
			userService.updateUserDetails(user, userId);
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in User:::" + ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in USer:::" + ex);
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/delete/user/{userId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "delete a user", response = String.class)
	public ResponseEntity<String> deleteUserDetails(@PathVariable Integer userId) {
		try {
			userService.deleteUserDetails(userId);
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in deleteUserDetails:::" + ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in deleteUserDetails:::" + ex);
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
		
	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/add/projects", method = RequestMethod.POST, consumes = "application/JSON")
	@ApiOperation(value = "create a project", response = String.class)
	public ResponseEntity<String> saveProjectDetails(@RequestBody Project project) {
		try {
			projectService.addProject(project);
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in saveTaskManager:::" + ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in saveTaskManager:::" + ex);
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/retrieve/projects", method = RequestMethod.GET, produces = "application/JSON")
	@ApiOperation(value = "View a list of projects", response = List.class)
	public ResponseEntity<List<Project>> retrieveProjects() {
		
		List<Project> projects = null;
		try {
			projects = projectService.getAllProjects();
			for(Project proj: projects) {
				System.out.println("proj id:::"+proj.getProjectId());
				System.out.println("proj total task:::"+proj.getTotalTask());
			}
			
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in retrieveProjects:::" + ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in retrieveProjects:::" + ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(projects, HttpStatus.OK);

	}	
	
	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/update/projects/{projectId}", method = RequestMethod.POST, consumes = "application/JSON")
	@ApiOperation(value = "create a project", response = String.class)
	public ResponseEntity<String> updateProjectDetails(@PathVariable Integer projectId, @RequestBody Project project) {
		try {
			projectService.updateProject(projectId, project);
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in updateProjectDetails:::" + ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in updateProjectDetails:::" + ex);
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/add/parentTask", method = RequestMethod.POST, consumes = "application/JSON")
	@ApiOperation(value = "create a new parent task", response = String.class)
	public ResponseEntity<String> addParentTask(@RequestBody Parent parent) {
		try {
			parentTaskService.addParentTask(parent);
		} catch (BadRequestException ex) {
			System.out.println("Error occurred in addParentTask:::" + ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in addParentTask:::" + ex);
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ResponseBody
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/retrieve/parentTasks", method = RequestMethod.GET, produces = "application/JSON")
	@ApiOperation(value = "View a list of available parent task", response = Iterable.class)
	public ResponseEntity<Iterable<Parent>> retrieveParentTasks() {
		
		Iterable<Parent> listOfTask = null;
		try {
			listOfTask = parentTaskService.getAllParentTasks();
		} catch (Exception ex) {
			System.out.println("Error occurred in retrieveTaskManager:::" + ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(listOfTask, HttpStatus.OK);

	}
}
