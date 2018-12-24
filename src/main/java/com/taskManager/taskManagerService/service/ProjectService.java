package com.taskManager.taskManagerService.service;

import com.taskManager.taskManagerService.domain.Project;

public interface ProjectService {
	public void addProject(Project project) throws Exception;
	public Iterable<Project> getAllProjects() throws Exception;
	
}
