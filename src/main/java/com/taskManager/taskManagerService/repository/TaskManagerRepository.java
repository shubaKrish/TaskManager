package com.taskManager.taskManagerService.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskManager.taskManagerService.domain.TaskManager;

@Repository
@Transactional
public interface TaskManagerRepository extends CrudRepository<TaskManager, Integer> {
    List<TaskManager> findAllByOrderByParentIdDesc();
    TaskManager findOneByTaskId(Integer taskId);
    @Query(value="select task.taskId,task.task,task.priority,task.parentId,"
    		+ "task.projectId,task.startDate,task.endDate, "
    		+ "parent.parentTask,usr.userId "
    		+ "from task_manager task "
    		+ "left join parent parent on parent.parentId = task.parentId\r\n" + 
    		"left join user usr on usr.taskId = task.taskId" ,nativeQuery=true)
	List<Object[]> findTaskByParentJoin();
}
