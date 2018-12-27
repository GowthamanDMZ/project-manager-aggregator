package com.tool.pmanager.request;

import com.tool.pmanager.pojo.ProjectBO;

public class GetProjectRequest {
	
	private ProjectBO projectVO;

	private String action;

	public ProjectBO getProjectVO() {
		return projectVO;
	}

	public void setProjectVO(ProjectBO projectVO) {
		this.projectVO = projectVO;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}
