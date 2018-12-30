package com.taskManager.taskManagerService.repository;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taskManager.taskManagerService.Environment;
import com.taskManager.taskManagerService.domain.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(Environment.UNIT_TEST)
@TestPropertySource("classpath:application-unit-test.properties")
public class ProjectRepositoryTest {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
    @Before
    public void setup() {
    	projectRepository.deleteAll();
    }
    
	@Test
	public void testSaveAndFindProjectDetails() {	  
	  Project project = new Project();
	  project.setProject("test project");
	  project.setStartDate(new Date(System.currentTimeMillis()));
	  Project savedProject = projectRepository.save(project);
	  
	  Project availableProject = projectRepository.findOneByProjectId(savedProject.getProjectId());
	  
	  Assert.assertEquals("test project", availableProject.getProject());
	}
	
	
	
}
