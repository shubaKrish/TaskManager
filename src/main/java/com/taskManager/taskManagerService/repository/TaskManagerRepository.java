package com.taskManager.taskManagerService.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskManager.taskManagerService.domain.TaskManager;

@Repository
@Transactional
public interface TaskManagerRepository extends CrudRepository<TaskManager, Integer> {
    List<TaskManager> findAllByOrderByParentIdDesc();
    TaskManager findOneByTaskId(Integer taskId);
}
