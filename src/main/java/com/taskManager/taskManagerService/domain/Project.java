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
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectId", nullable = false, unique = true)
	private int projectId;

	@Column(name = "project", nullable = false, unique = true)
	private String project;

	@Column(name = "priority", nullable = false)
	private int priority;

	@Column(name = "startDate", nullable = true)
	private Date startDate;

	@Column(name = "endDate", nullable = true)
	private Date endDate;

	@Transient
	private User user;
	
	@Transient
	private int totalTask;
	
	@Transient
	private int completedTask;

	@Transient
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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
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

	public int getTotalTask() {
		return totalTask;
	}

	public void setTotalTask(int totalTask) {
		this.totalTask = totalTask;
	}

	public int getCompletedTask() {
		return completedTask;
	}

	public void setCompletedTask(int completedTask) {
		this.completedTask = completedTask;
	}
}
