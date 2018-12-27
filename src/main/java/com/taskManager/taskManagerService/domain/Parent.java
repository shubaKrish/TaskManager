package com.taskManager.taskManagerService.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parent")
public class Parent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="parentId",nullable=false,unique=true)
	private int parentId;
	
	@Column(name="parentTask",nullable=false,unique=true)
	private String parentTask;
	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getParentTask() {
		return parentTask;
	}
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	
}
