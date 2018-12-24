package com.taskManager.taskManagerService.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskManager.taskManagerService.domain.User;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findOneByUserId(int userId);
}
