package com.tool.pmanager.response;

import java.util.List;

import com.tool.pmanager.pojo.UserBO;


public class GetUserResponse {
	
	private List<UserBO> userVO;

	private String status;

	public List<UserBO> getUserVO() {
		return userVO;
	}

	public void setUserVO(List<UserBO> userVO) {
		this.userVO = userVO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}