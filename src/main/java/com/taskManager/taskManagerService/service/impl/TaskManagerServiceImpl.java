package com.taskManager.taskManagerService.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskManager.taskManagerService.Exception.BadRequestException;
import com.taskManager.taskManagerService.domain.Parent;
import com.taskManager.taskManagerService.domain.TaskManager;
import com.taskManager.taskManagerService.domain.User;
import com.taskManager.taskManagerService.repository.TaskManagerRepository;
import com.taskManager.taskManagerService.repository.UserRepository;
import com.taskManager.taskManagerService.service.TaskManagerService;

@Service("taskManagerService")
public class TaskManagerServiceImpl implements TaskManagerService {

	@Autowired
	private TaskManagerRepository taskManagerRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Iterable<TaskManager> getAllTask() throws Exception {
		List<Object[]> results = taskManagerRepository.findTaskByParentJoin();
		List<TaskManager> tasks = new ArrayList<>();
		if (results != null && results.size() > 0) {
			for (Object[] result : results) {
				TaskManager task = new TaskManager();
				task.setTaskId(((Number) result[0]).intValue());
				task.setTask((String) result[1]);
				if(result[2]!=null) {
				task.setPriority(((Number) result[2]).intValue());	
				}
				if(result[4]!=null) {
				task.setProjectId(((Number) result[4]).intValue());
				}
				if(result[5]!=null) {
					task.setStartDate((Date) result[5]);
				}
				if(result[6]!=null) {
					task.setEndDate((Date) result[6]);
				}
				if(result[3]!=null) {
					int parentID = ((Number) result[3]).intValue();
					task.setParentId(parentID);
					Parent parent = new Parent();
					parent.setParentId(parentID);
					parent.setParentTask((String) result[7]);
					task.setParent(parent);
				}
				if(result[8]!=null) {
					int userId = ((Number) result[8]).intValue();
					User user = new User();
					user.setUserId(userId);
					task.setUser(user);
				}
				if(result[9]!=null) {
					task.setStatus((String) result[9]);
				}
				tasks.add(task);
			}
		}
		return tasks;

	}

	

	@Override
	public void addTask(TaskManager task) throws Exception {
		TaskManager savedTask = taskManagerRepository.save(task);
		if(task!= null && task.getUser()!=null) {
			User existignUser = userRepository.findOneByUserId(task.getUser().getUserId());
			if (existignUser !=null) {
				existignUser.setTaskId(savedTask.getTaskId());
				userRepository.save(existignUser);
			}
		}
	}

	@Override
	public void updateTask(TaskManager task, Integer taskId, boolean endTask) throws Exception {

		if (task == null) {
			throw new BadRequestException("No task found to update the database!");
		}
		if (task != null && taskId != task.getTaskId()) {
			throw new BadRequestException("No matching id found in request object!");
		}

		TaskManager existingTask = taskManagerRepository.findOneByTaskId(task.getTaskId());
		if (existingTask == null) {
			throw new BadRequestException("No existing record found iin Database!");
		} else if (existingTask != null && existingTask.getTaskId() != taskId) {
			throw new BadRequestException("No matching id found in Database!");
		} else {
			if (!endTask) {
				existingTask.setTask(task.getTask());
				existingTask.setStartDate(task.getStartDate());
				existingTask.setEndDate(task.getEndDate());
				existingTask.setParentId(task.getParentId());
				existingTask.setPriority(task.getPriority());
				taskManagerRepository.save(existingTask);
			} else {
				existingTask.setStatus(task.getStatus());
				existingTask.setEndDate(task.getEndDate());
				taskManagerRepository.save(existingTask);
			}
		}

	}


}
