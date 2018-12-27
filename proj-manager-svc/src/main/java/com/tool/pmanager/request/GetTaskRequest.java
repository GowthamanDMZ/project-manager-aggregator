package com.tool.pmanager.request;

import com.tool.pmanager.pojo.TaskBO;

public class GetTaskRequest {
	
	private TaskBO taskVO;

	private String action;

	public TaskBO getTaskVO() {
		return taskVO;
	}

	public void setTaskVO(TaskBO taskVO) {
		this.taskVO = taskVO;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}