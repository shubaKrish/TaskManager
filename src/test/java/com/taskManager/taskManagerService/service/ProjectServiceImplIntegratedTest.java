package com.taskManager.taskManagerService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taskManager.taskManagerService.Environment;
import com.taskManager.taskManagerService.Exception.BadRequestException;
import com.taskManager.taskManagerService.domain.Project;
import com.taskManager.taskManagerService.repository.ProjectRepository;
import com.taskManager.taskManagerService.service.impl.ProjectServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
@TestPropertySource("classpath:application-unit-test.properties")
public class ProjectServiceImplIntegratedTest {
	
	@Autowired
	private ProjectServiceImpl projectServiceImpl;
	
	@Autowired
	private ProjectRepository projectRepository;
    
	@Before
	public void setup() {
		projectRepository.deleteAll();
	}
	
	@Test
	public void testAddProject() throws Exception {
	  Project project = new Project();
	  project.setProject("test");
	  project.setStartDate(new Date(System.currentTimeMillis()));
	  projectServiceImpl.addProject(project);
	}
	
	
	@Test
	public void testUpdateProjectDetails() throws Exception {
	  Project project = new Project();
	  project.setProject("test");
	  project.setStartDate(new Date(System.currentTimeMillis()));
	  Project saved = projectRepository.save(project);
	  Project proj = new Project();
	  proj.setProject("test1");
	  proj.setStartDate(new Date(System.currentTimeMillis()));
	  proj.setProjectId(saved.getProjectId());
	  projectServiceImpl.updateProject(saved.getProjectId(), proj);
	  Project updatedProject = projectRepository.findOneByProjectId(saved.getProjectId());
	  assertNotNull(updatedProject);
	  assertEquals("test1", updatedProject.getProject());
	}
	
	@Test(expected=BadRequestException.class)
	public void testUpdateProjectDetailsWithNoMacthingId() throws Exception {
	  Project project = new Project();
	  project.setProject("test");
	  project.setStartDate(new Date(System.currentTimeMillis()));
	  Project saved = projectRepository.save(project);
	  Project proj = new Project();
	  proj.setProject("test1");
	  proj.setStartDate(new Date(System.currentTimeMillis()));
	  proj.setProjectId(saved.getProjectId());
	  projectServiceImpl.updateProject(234, proj);
	 
	}
	
	@Test(expected=BadRequestException.class)
	public void testUpdateProjectDetailsWithNoProjectId() throws Exception {
	  Project project = new Project();
	  project.setProject("test");
	  project.setStartDate(new Date(System.currentTimeMillis()));
	  Project saved = projectRepository.save(project);
	  Project proj = new Project();
	  proj.setProject("test1");
	  proj.setStartDate(new Date(System.currentTimeMillis()));
	  proj.setProjectId(saved.getProjectId());
	  projectServiceImpl.updateProject(0, proj);
	 
	}
	
	@Test(expected=BadRequestException.class)
	public void testUpdateProjectDetailsWithNoProjectDetails() throws Exception {
	  Project project = new Project();
	  project.setProject("test");
	  project.setStartDate(new Date(System.currentTimeMillis()));
	  Project saved = projectRepository.save(project);
	  
	  projectServiceImpl.updateProject(saved.getProjectId(), null);
	 
	}
	

}
