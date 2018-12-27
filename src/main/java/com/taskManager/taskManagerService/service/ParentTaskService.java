package com.taskManager.taskManagerService.service;

import com.taskManager.taskManagerService.domain.Parent;

public interface ParentTaskService {
   
	void addParentTask(Parent parent) throws Exception;
	Iterable<Parent> getAllParentTasks();
}
