package com.taskManager.taskManagerService.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskManager.taskManagerService.Exception.BadRequestException;
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
		return taskManagerRepository.findAll();

	}

	@Override
	public Optional<TaskManager> getSingleTask(Integer task_id) throws Exception {
		return taskManagerRepository.findById(task_id);

	}

	@Override
	public void addTask(TaskManager task) throws Exception {
		if (task != null && task.getParentTask() != null && !task.getParentTask().isEmpty()) {
			List<TaskManager> taskList = taskManagerRepository.findAllByOrderByParentIdDesc();
			if (taskList.size() > 0) {
				task.setParentId(taskList.get(0).getParentId() + 1);
			} else {
				task.setParentId(1);
			}
		}
		TaskManager savedTask = taskManagerRepository.save(task);
		User existignUser = userRepository.findOneByUserId(task.getUser().getUserId());
		if(existignUser==null) {
			throw new Exception("User not found in database to assign task");
		} else {
			existignUser.setTaskId(savedTask.getTaskId());
			userRepository.save(existignUser);
		}
	}

	@Override
	public void updateTask(TaskManager task, Integer taskId, boolean endTask) throws Exception {
		
		if(task==null) {
			throw new BadRequestException("No task found to update the database!");
		}
		if(task!=null && taskId!=task.getTaskId()) {
			throw new BadRequestException("No matching id found in request object!");
		}		
		
		TaskManager existingTask = taskManagerRepository.findOneByTaskId(task.getTaskId());
		if(existingTask==null) {
			throw new BadRequestException("No existing record found iin Database!");
		} else {
			if(existingTask!=null && existingTask.getTaskId()!=taskId) {
				throw new BadRequestException("No matching id found in Database!");
			}
			if(!endTask) {
				if(task.getParentTask()!=null && !task.getParentTask().isEmpty()) {
					if(existingTask.getParentId()==0) {
						List<TaskManager> taskList = taskManagerRepository.findAllByOrderByParentIdDesc();
						if (taskList.size() > 0) {
							task.setParentId(taskList.get(0).getParentId() + 1);
						} else {
							task.setParentId(1);
						}
					}
				} else {
					task.setParentId(0);
				}
				taskManagerRepository.save(task);
			} else {
				existingTask.setEndDate(new Date(System.currentTimeMillis()));
				taskManagerRepository.save(existingTask);
			}
			
		}
		

	}	
	
	
}
