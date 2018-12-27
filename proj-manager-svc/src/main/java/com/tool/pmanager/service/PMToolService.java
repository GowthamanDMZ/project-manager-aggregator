/**
 * ProjectManagerService.java
 *
 * Modification History
 *
 * Date        Version   Developer      Description
 * ---------   -------   ------------   --------------------------------------
 * 11/23/2018   1.0	 	 Cognizant		Initial version
 */
package com.tool.pmanager.service;

import com.tool.pmanager.exception.PMToolException;
import com.tool.pmanager.request.GetParentTaskRequest;
import com.tool.pmanager.request.GetProjectRequest;
import com.tool.pmanager.request.GetTaskRequest;
import com.tool.pmanager.request.GetUserRequest;
import com.tool.pmanager.response.GetParentTaskResponse;
import com.tool.pmanager.response.GetProjectResponse;
import com.tool.pmanager.response.GetTaskResponse;
import com.tool.pmanager.response.GetUserResponse;

public interface PMToolService {
	
	public GetTaskResponse viewTask(int projectId) throws PMToolException;
	
	public GetParentTaskResponse getParentTask() throws PMToolException;
	
	public GetProjectResponse getProject() throws PMToolException;

	public GetUserResponse getUser() throws PMToolException;
	
	public String updateTask(GetTaskRequest request) throws PMToolException;
	
	public String updateParentTask(GetParentTaskRequest request) throws PMToolException;
	
	public String updateProject(GetProjectRequest request) throws PMToolException;
	
	public String updateUser(GetUserRequest request) throws PMToolException;
	
}
