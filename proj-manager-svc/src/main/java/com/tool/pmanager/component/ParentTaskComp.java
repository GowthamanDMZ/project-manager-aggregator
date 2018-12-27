package com.tool.pmanager.component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="parent_task")
public class ParentTaskComp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="parent_id")
	private int parentId;
	
	@Column(name="parent_task")
	private String parentTask;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private ProjectComp projectEnt;

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

	public ProjectComp getProjectEnt() {
		return projectEnt;
	}

	public void setProjectEnt(ProjectComp projectEnt) {
		this.projectEnt = projectEnt;
	}

}
