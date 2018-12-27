package com.taskManager.taskManagerService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskManager.taskManagerService.domain.Parent;
import com.taskManager.taskManagerService.repository.ParentRepository;
import com.taskManager.taskManagerService.service.ParentTaskService;

@Service("parentTaskService")
public class ParentTaskServiceImpl implements ParentTaskService {
	
	@Autowired
	private ParentRepository parentRepository;

	@Override
	public void addParentTask(Parent parent) throws Exception {
		// TODO Auto-generated method stub
		parentRepository.save(parent);
	}

	@Override
	public Iterable<Parent> getAllParentTasks() {
		// TODO Auto-generated method stub
		return parentRepository.findAll();
	}
	
}
