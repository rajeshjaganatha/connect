package com.thoughtfocus.leave.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Projects")
public class Projects implements Serializable
{

	private static final long serialVersionUID = 1L;


	private int projectId;
	
		
	private String projectName;
	
	
	private List<TaskSummary> taskSummary;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PROJECT_ID")
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name="PROJECT_NAME")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projects")
	public List<TaskSummary> getTaskSummary() {
		return taskSummary;
	}

	public void setTaskSummary(List<TaskSummary> taskSummary) {
		this.taskSummary = taskSummary;
	}
	
}
