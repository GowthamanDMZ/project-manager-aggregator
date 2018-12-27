package com.tool.pmanager.request;

import com.tool.pmanager.pojo.UserBO;

public class GetUserRequest {
	
	private UserBO userVO;

	private String action;

	public UserBO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserBO userVO) {
		this.userVO = userVO;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}
