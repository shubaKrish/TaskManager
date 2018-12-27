package com.taskManager.taskManagerService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskManager.taskManagerService.domain.Parent;

@Repository
public interface ParentRepository extends CrudRepository<Parent, Integer> {
}
