package com.taskManager.taskManagerService.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskManager.taskManagerService.domain.Project;

@Repository
@Transactional
public interface ProjectRepository extends CrudRepository<Project, Integer> {
	@Query(value="select distinct sub.projectId, sub.project,sub.priority,sub.startDate,sub.endDate,sub.completedTask as completedTask,count(task2.taskId) as totalTask\r\n" + 
			" from (select proj.projectId as projectId, \r\n" + 
			"proj.project as project,\r\n" + 
			"proj.priority as priority,\r\n" + 
			"proj.startDate as startDate,\r\n" + 
			"proj.endDate as endDate,\r\n" + 
			"count(task1.taskId) as completedTask\r\n" + 
			"from project proj \r\n" + 
			"left join task_manager task1\r\n" + 
			"on task1.projectId = proj.projectId\r\n" + 
			"and task1.endDate IS NOT NULL\r\n" + 
			"group by proj.projectId, proj.project,proj.priority,proj.startDate,proj.endDate\r\n" + 
			") as sub\r\n" + 
			"left join task_manager task2\r\n" + 
			"on task2.projectId = sub.projectId \r\n" + 
			"group by sub.projectId, sub.project,sub.priority,sub.startDate,sub.endDate,sub.completedTask",nativeQuery=true)
	List<Object[]> findByJoin();
	
	Project findOneByProjectId(int projectId);
}
