package com.taskManager.taskManagerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.taskManager.taskManagerService.repository.TaskManagerRepository;
import com.taskManager.taskManagerService.repository.UserRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {TaskManagerRepository.class, UserRepository.class})
//@PropertySource(value = {"classpath:application.properties"})
@EnableSwagger2

public class TaskManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerServiceApplication.class, args);
	}
}
