package com.taskManager.taskManagerService.service;

import java.util.Optional;

import com.taskManager.taskManagerService.domain.TaskManager;

public interface TaskManagerService {
   
	Iterable<TaskManager> getAllTask() throws Exception;
	void addTask(TaskManager task) throws Exception;
	void updateTask(TaskManager task, Integer taskId, boolean endTask) throws Exception;
}
