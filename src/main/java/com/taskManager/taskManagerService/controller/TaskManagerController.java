package com.taskManager.taskManagerService.controller;

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
import com.taskManager.taskManagerService.domain.TaskManager;
import com.taskManager.taskManagerService.service.impl.TaskManagerServiceImpl;

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
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			System.out.println("Error occurred in retrieveTaskManager:::" + ex);
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
}
