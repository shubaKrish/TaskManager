package com.taskManager.taskManagerService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskManager.taskManagerService.domain.Project;
import com.taskManager.taskManagerService.domain.User;
import com.taskManager.taskManagerService.repository.ProjectRepository;
import com.taskManager.taskManagerService.repository.UserRepository;
import com.taskManager.taskManagerService.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private UserRepository userRepository;

	@Override
	public void addProject(Project project) throws Exception {
		// TODO Auto-generated method stub
		Project savedProject = projectRepository.save(project);
		System.out.println("Project id::"+savedProject.getProjectId());
		System.out.println("Project user id::"+project.getUser().getUserId());
		User user = userRepository.findOneByUserId(project.getUser().getUserId());
		if(user==null) {
			throw new Exception("unable to find the user in database to assign");
		} else {
			user.setProjectId(savedProject.getProjectId());
			userRepository.save(user);
		}
	}

	@Override
	public Iterable<Project> getAllProjects() throws Exception {
		// TODO Auto-generated method stub
		return projectRepository.findAll();
	}
    
	
}
