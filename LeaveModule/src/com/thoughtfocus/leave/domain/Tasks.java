package com.thoughtfocus.leave.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;


@Entity
@Table(name="TASKS")
public class Tasks implements Serializable
{

	private static final long serialVersionUID = 1L;


	private int taskId;
	
	private int projectId;
	
	private String taskName;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TASK_ID")
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	@Column(name="PROJECT_ID")
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name="TASK_NAME")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	

	
	
}
