package com.tool.pmanager.response;

import java.util.List;

import com.tool.pmanager.pojo.ProjectBO;


public class GetProjectResponse {
	
	private List<ProjectBO> projectVO;

	private String status;

	public List<ProjectBO> getProjectVO() {
		return projectVO;
	}

	public void setProjectVO(List<ProjectBO> projectVO) {
		this.projectVO = projectVO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
