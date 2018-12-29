package com.taskManager.taskManagerService.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="task_manager")
public class TaskManager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="taskId",nullable=false,unique=true)
	private int taskId;
	@Column(name="parentId",nullable=true)
	private int parentId;	
	@Column(name="priority",nullable=true)
	private int priority;	
	@Column(name="startDate",nullable=false)
	private Date startDate;	
	@Column(name="endDate",nullable=true)
	private Date endDate;
	@Column(name="task",nullable=false)
	private String task;
	@Column(name="projectId",nullable=false)
	private int projectId;	
	
	@Transient
	private Parent parent;
	
	@Transient
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	
	
}
