package com.tool.pmanager.response;

import java.util.List;

import com.tool.pmanager.pojo.TaskBO;


public class GetTaskResponse {
	
	private List<TaskBO> taskVO;

	private String status;

	public List<TaskBO> getTaskVO() {
		return taskVO;
	}

	public void setTaskVO(List<TaskBO> taskVO) {
		this.taskVO = taskVO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
