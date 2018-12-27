package com.tool.pmanager.request;

import com.tool.pmanager.pojo.ParentTaskBO;

public class GetParentTaskRequest {
	
	private ParentTaskBO parentTaskVO;

	private String action;

	public ParentTaskBO getParentTaskVO() {
		return parentTaskVO;
	}

	public void setParentTaskVO(ParentTaskBO parentTaskVO) {
		this.parentTaskVO = parentTaskVO;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}