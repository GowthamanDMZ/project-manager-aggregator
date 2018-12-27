package com.tool.pmanager.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tool.pmanager.constants.PMToolConstants;
import com.tool.pmanager.exception.PMToolException;
import com.tool.pmanager.request.GetParentTaskRequest;
import com.tool.pmanager.request.GetProjectRequest;
import com.tool.pmanager.request.GetTaskRequest;
import com.tool.pmanager.request.GetUserRequest;
import com.tool.pmanager.response.GetParentTaskResponse;
import com.tool.pmanager.response.GetProjectResponse;
import com.tool.pmanager.response.GetTaskResponse;
import com.tool.pmanager.response.GetUserResponse;
import com.tool.pmanager.service.PMToolService;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
public class PMToolController implements PMToolConstants {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PMToolService pmToolService;
	
	@RequestMapping(value = "/viewTask", method = RequestMethod.POST, headers = "Accept=*/*", consumes = {
			"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetTaskResponse viewTask(@RequestBody @Valid GetTaskRequest request) throws PMToolException {
		GetTaskResponse getTaskResponse = new GetTaskResponse();
		try {
			getTaskResponse = pmToolService.viewTask(request.getTaskVO().getProjectId());
		} catch(PMToolException e) {
			logger.error("Exception in  ProjectManagerController viewTask : "+ e);
			throw new PMToolException(e.getErrorCode(), e.getErrorMessage(), e.getStatus());
		}
		return getTaskResponse;
	}
	
	@RequestMapping(value = "/getParentTask", method = RequestMethod.GET, headers = "Accept=*/*", produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetParentTaskResponse getParentTask() throws PMToolException {
		GetParentTaskResponse getParentTaskResponse = new GetParentTaskResponse();
		try {
			getParentTaskResponse = pmToolService.getParentTask();
		} catch(PMToolException e) {
			logger.error("Exception in  ProjectManagerController getParentTask : "+ e);
			throw new PMToolException(e.getErrorCode(), e.getErrorMessage(), e.getStatus());
		}
		return getParentTaskResponse;
	}

	@RequestMapping(value = "/getProject", method = RequestMethod.GET, headers = "Accept=*/*", produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetProjectResponse getProject() throws PMToolException {
		GetProjectResponse getProjectResponse = new GetProjectResponse();
		try {
			getProjectResponse = pmToolService.getProject();
		} catch(PMToolException e) {
			logger.error("Exception in  ProjectManagerController getProject : "+ e);
			throw new PMToolException(e.getErrorCode(), e.getErrorMessage(), e.getStatus());
		}
		return getProjectResponse;
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET, headers = "Accept=*/*", produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetUserResponse getUser() throws PMToolException {
		GetUserResponse getUserResponse = new GetUserResponse();
		try {
			getUserResponse = pmToolService.getUser();
		} catch(PMToolException e) {
			logger.error("Exception in  ProjectManagerController getUser : "+ e);
			throw new PMToolException(e.getErrorCode(), e.getErrorMessage(), e.getStatus());
		}
		return getUserResponse;
	}
	
	@RequestMapping(value = "/updateTask", method = RequestMethod.POST, headers = "Accept=*/*", consumes = {
			"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetTaskResponse updateTask(@RequestBody @Valid GetTaskRequest request) throws PMToolException {
		GetTaskResponse getTaskResponse = new GetTaskResponse();
		String status = EMPTY;
		try {
			status = pmToolService.updateTask(request);
			getTaskResponse.setStatus(status);
		} catch(PMToolException e) {
			logger.error("Exception in  ProjectManagerController updateTask : "+ e);
			throw new PMToolException(e.getErrorCode(), e.getErrorMessage(), e.getStatus());
		}
		return getTaskResponse;
	}
	
	@RequestMapping(value = "/updateParentTask", method = RequestMethod.POST, headers = "Accept=*/*", consumes = {
			"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetParentTaskResponse updateParentTask(@RequestBody @Valid GetParentTaskRequest request) throws PMToolException {
		GetParentTaskResponse getParentTaskResponse = new GetParentTaskResponse();
		String status = EMPTY;
		try {
			status = pmToolService.updateParentTask(request);
			getParentTaskResponse.setStatus(status);
		} catch(PMToolException e) {
			logger.error("Exception in  ProjectManagerController updateParentTask : "+ e);
			throw new PMToolException(e.getErrorCode(), e.getErrorMessage(), e.getStatus());
		}
		return getParentTaskResponse;
	}
	
	@RequestMapping(value = "/updateProject", method = RequestMethod.POST, headers = "Accept=*/*", consumes = {
			"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetProjectResponse updateProject(@RequestBody @Valid GetProjectRequest request) throws PMToolException {
		GetProjectResponse getProjectResponse = new GetProjectResponse();
		String status = EMPTY;
		try {
			status = pmToolService.updateProject(request);
			getProjectResponse.setStatus(status);
		} catch(PMToolException e) {
			logger.error("Exception in  ProjectManagerController updateProject : "+ e);
			throw new PMToolException(e.getErrorCode(), e.getErrorMessage(), e.getStatus());
		}
		return getProjectResponse;
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, headers = "Accept=*/*", consumes = {
			"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
	public @ResponseBody GetUserResponse updateUser(@RequestBody @Valid GetUserRequest request) throws PMToolException {
		GetUserResponse getUserResponse = new GetUserResponse();
		String status = EMPTY;
		try {
			status = pmToolService.updateUser(request);
			getUserResponse.setStatus(status);
		} catch(PMToolException e) {
			logger.error("Exception in  ProjectManagerController updateUser : "+ e);
			throw new PMToolException(e.getErrorCode(), e.getErrorMessage(), e.getStatus());
		}
		return getUserResponse;
	}
}