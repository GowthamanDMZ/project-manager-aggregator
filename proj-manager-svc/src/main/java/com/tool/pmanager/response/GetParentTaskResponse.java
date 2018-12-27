package com.tool.pmanager.response;

import java.util.List;

import com.tool.pmanager.pojo.ParentTaskBO;


public class GetParentTaskResponse {
	
	private List<ParentTaskBO> parentTaskVO;

	private String status;

	public List<ParentTaskBO> getParentTaskVO() {
		return parentTaskVO;
	}

	public void setParentTaskVO(List<ParentTaskBO> parentTaskVO) {
		this.parentTaskVO = parentTaskVO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
