package com.thoughtfocus.leave.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TASK_SUMMARY")
public class TaskSummary implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int taskSummaryId;
	
	private int userId;

	private int projectId;
	
	private int taskId;
	
	private Date taskDate;
	
	private double hours;
		
	private String taskDesc;
	
	private Projects projects;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	public int getTaskSummaryId() {
		return taskSummaryId;
	}

	public void setTaskSummaryId(int taskSummaryId) {
		this.taskSummaryId = taskSummaryId;
	}

	@Column(name="USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name="PROJECT_ID")
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name="TASK_ID")
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	@Column(name="TASK_DATE")
	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	@Column(name="TASK_HOURS")
	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	@Column(name="TASK_DESC")
	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_ID", insertable = false, updatable = false)
	public Projects getProjects() {
		return projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}
	
	
	}
