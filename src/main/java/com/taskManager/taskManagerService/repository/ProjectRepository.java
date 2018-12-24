package com.taskManager.taskManagerService.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskManager.taskManagerService.domain.Project;

@Repository
@Transactional
public interface ProjectRepository extends CrudRepository<Project, Integer> {
	
}
