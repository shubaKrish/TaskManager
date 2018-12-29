package com.taskManager.taskManagerService.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskManager.taskManagerService.Exception.BadRequestException;
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
		if(project.getUser()!=null) {
			User user = userRepository.findOneByUserId(project.getUser().getUserId());
			if(user!=null) {
				user.setProjectId(savedProject.getProjectId());
				userRepository.save(user);
			}
		}
	}

	@Override
	public List<Project> getAllProjects() throws Exception {
		// TODO Auto-generated method stub
		List<Object[]> results = projectRepository.findByJoin();
		List<Project> project = new ArrayList<>();
		if(results!=null && results.size()>0) {
		for(Object[] result: results) {
			Project proj = new Project();
			proj.setProjectId(((Number) result[0]).intValue());
			proj.setProject((String)result[1]);
			if(result[2]!=null) {
			proj.setPriority(((Number) result[2]).intValue());
			}
			if(result[3]!=null) {
			proj.setStartDate((Date)result[3]);
			}
			if(result[4]!=null) {
			proj.setEndDate((Date)result[4]);
			}
			if(result[5]!=null) {
			proj.setCompletedTask(((Number) result[5]).intValue());
			}
			if(result[6]!=null) {
			proj.setTotalTask(((Number) result[6]).intValue());
			}
			User user = userRepository.findOneByProjectId(proj.getProjectId());
			proj.setUser(user);
			project.add(proj);
		}
		}
		return project;
	}
	
	@Override
	public void updateProject(Integer projectId, Project project) throws Exception {
		// TODO Auto-generated method stub
		if(projectId==0 || project==null) {
			throw new BadRequestException("No project details found!");
		} else if(projectId!=project.getProjectId()) {
			throw new BadRequestException("No matching id found in Request param!");
		}
		Project existingProject = projectRepository.findOneByProjectId(projectId);
		if(existingProject==null) {
			throw new BadRequestException("No existing project details found in database to update!");
		} else {
			existingProject.setPriority(project.getPriority());
			existingProject.setStartDate(project.getStartDate());
			existingProject.setEndDate(project.getEndDate());
			existingProject.setProject(project.getProject());
			projectRepository.save(existingProject);
			if(project.getUser()!=null) {
				User user = userRepository.findOneByUserId(project.getUser().getUserId());
				if(user!=null) {				
					user.setProjectId(project.getProjectId());
					userRepository.save(user);
				}
			}
		}
		
	}
    
	
}
