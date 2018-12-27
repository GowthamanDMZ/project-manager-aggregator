package com.tool.pmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tool.pmanager.component.ParentTaskComp;
import com.tool.pmanager.component.ProjectComp;
import com.tool.pmanager.component.TaskComp;
import com.tool.pmanager.component.UserComp;
import com.tool.pmanager.constants.PMToolConstants;
import com.tool.pmanager.exception.PMToolException;
import com.tool.pmanager.pojo.ParentTaskBO;
import com.tool.pmanager.pojo.ProjectBO;
import com.tool.pmanager.pojo.TaskBO;
import com.tool.pmanager.pojo.UserBO;
import com.tool.pmanager.repository.ParentTaskRepository;
import com.tool.pmanager.repository.ProjectRepository;
import com.tool.pmanager.repository.TaskRepository;
import com.tool.pmanager.repository.UserRepository;
import com.tool.pmanager.request.GetParentTaskRequest;
import com.tool.pmanager.request.GetProjectRequest;
import com.tool.pmanager.request.GetTaskRequest;
import com.tool.pmanager.request.GetUserRequest;
import com.tool.pmanager.response.GetParentTaskResponse;
import com.tool.pmanager.response.GetProjectResponse;
import com.tool.pmanager.response.GetTaskResponse;
import com.tool.pmanager.response.GetUserResponse;
import com.tool.pmanager.util.PMToolUtil;


@Service
public class PMToolServiceImpl implements PMToolService, PMToolConstants {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ParentTaskRepository parentTaskRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public GetTaskResponse viewTask(int projectId) throws PMToolException {
		// TODO Auto-generated method stub
		GetTaskResponse getTaskResponse = new GetTaskResponse();
		List<TaskBO> taskVOList = null;
		try {
			List<TaskComp> taskEntList = getTaskByProjectId(projectId);
			if(null != taskEntList && !taskEntList.isEmpty()) {
	        	taskVOList = new ArrayList<>();
	        	
	        	for(TaskComp taskEnt : taskEntList) {
		        	TaskBO task = new TaskBO();
		        	task.setTaskId(taskEnt.getTaskId());
		        	task.setTask(taskEnt.getTask());
		        	
		        	task.setParentId(null != taskEnt.getParentTaskEnt() ? taskEnt.getParentTaskEnt().getParentId() : 0);
		        	task.setParentTaskName(null != taskEnt.getParentTaskEnt() ? taskEnt.getParentTaskEnt().getParentTask() : "");
		        	
		        	task.setProjectId(taskEnt.getProjectEnt().getProjectId());
		        	task.setProjectName(taskEnt.getProjectEnt().getProject());
		        	
		        	task.setUserId(taskEnt.getProjectEnt().getUserEnt().getUserId());
		        	task.setUserFName(taskEnt.getProjectEnt().getUserEnt().getFname());
		        	task.setUserLName(taskEnt.getProjectEnt().getUserEnt().getLname());
		        	
		        	task.setPriority(taskEnt.getPriority());
		        	task.setStartDate(PMToolUtil.dateToString(taskEnt.getStartDate()));
		        	task.setEndDate(PMToolUtil.dateToString(taskEnt.getEndDate()));
		        	task.setStatus(taskEnt.getStatus());
		        	
		        	taskVOList.add(task);
	        	}
	        }
	        getTaskResponse.setTaskVO(taskVOList);
	        getTaskResponse.setStatus("Success");
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return getTaskResponse;
	}

	@Override
	public GetParentTaskResponse getParentTask() throws PMToolException {
		// TODO Auto-generated method stub
		GetParentTaskResponse getParentTaskResponse = new GetParentTaskResponse();
		List<ParentTaskBO> parentTaskVOList = null;
		List<ParentTaskComp> parentTaskEntList = null;
		try {
			parentTaskEntList = parentTaskRepository.findAll();
			if(null != parentTaskEntList && !parentTaskEntList.isEmpty()) {
	        	parentTaskVOList = new ArrayList<>();
	        	
	        	for(ParentTaskComp parentTaskEnt : parentTaskEntList) {
	        		ParentTaskBO parentTask = new ParentTaskBO();
	        		parentTask.setParentId(parentTaskEnt.getParentId());
	        		parentTask.setParentTaskName(parentTaskEnt.getParentTask());
	        		parentTask.setProjectId(null != parentTaskEnt.getProjectEnt() ? parentTaskEnt.getProjectEnt().getProjectId() : 0);
		        	
	        		parentTaskVOList.add(parentTask);
	        	}
	        }
	        getParentTaskResponse.setParentTaskVO(parentTaskVOList);
	        getParentTaskResponse.setStatus("Success");
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return getParentTaskResponse;
	}
	
	@Override
	public GetProjectResponse getProject() throws PMToolException {
		// TODO Auto-generated method stub
		GetProjectResponse getProjectResponse = new GetProjectResponse();
		List<ProjectBO> projectVOList = null;
		int completedTaskCount = 0;
		try {
			List<ProjectComp> projectEntList = projectRepository.findAll();
			if(null != projectEntList && !projectEntList.isEmpty()) {
	        	projectVOList = new ArrayList<>();
	        	
	        	for(ProjectComp projectEnt : projectEntList) {
	        		ProjectBO project = new ProjectBO();
	        		project.setProjectId(projectEnt.getProjectId());
	        		project.setProject(projectEnt.getProject());
	        		project.setPriority(projectEnt.getPriority());
	        		project.setStartDate(PMToolUtil.dateToString(projectEnt.getStartDate()));
	        		project.setEndDate(PMToolUtil.dateToString(projectEnt.getEndDate()));
	        		project.setEmpId(null != projectEnt.getUserEnt() ? projectEnt.getUserEnt().getUserId() : 0);
		        	
	        		List<TaskComp> taskEntList = getTaskByProjectId(projectEnt.getProjectId());
	        		if(null != taskEntList && !taskEntList.isEmpty()) {
	        			for(TaskComp taskEnt : taskEntList) {
	        				if(null != taskEnt.getStatus() && STATUS_COMPLETED.equalsIgnoreCase(taskEnt.getStatus())) {
	        					completedTaskCount ++;
	        				}
	        			}
	        			project.setNoOfTask(taskEntList.size());
	        			project.setNoOfCompletedTask(completedTaskCount);
	        			completedTaskCount = 0;
	        		}
		        	projectVOList.add(project);
	        	}
	        }
			getProjectResponse.setProjectVO(projectVOList);
			getProjectResponse.setStatus("Success");
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return getProjectResponse;
	}

	@Override
	public GetUserResponse getUser() throws PMToolException {
		// TODO Auto-generated method stub
		GetUserResponse getUserResponse = new GetUserResponse();
		List<UserBO> userVOList = null;
		try {
			List<UserComp> userEntList = userRepository.findAll();
			if(null != userEntList && !userEntList.isEmpty()) {
	        	userVOList = new ArrayList<>();
	        	
	        	for(UserComp userEnt : userEntList) {
		        	UserBO user = new UserBO();
		        	user.setUserId(userEnt.getUserId());
		        	user.setFname(userEnt.getFname());
		        	user.setLname(userEnt.getLname());
		        	user.setEmpId(userEnt.getEmpId());
		        	
		        	userVOList.add(user);
	        	}
	        }
			getUserResponse.setUserVO(userVOList);
			getUserResponse.setStatus("Success");
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return getUserResponse;
	}

	@Override
	public String updateTask(GetTaskRequest request) throws PMToolException {
		// TODO Auto-generated method stub
		TaskComp taskEnt = new TaskComp();
		String status = EMPTY;
		try {
			ProjectComp projectEnt = projectRepository.findByProjectId(request.getTaskVO().getProjectId());
			ParentTaskComp parentTaskEnt = parentTaskRepository.findByParentTaskId(request.getTaskVO().getParentId());
			
			taskEnt.setTaskId(request.getTaskVO().getTaskId());
			taskEnt.setTask(request.getTaskVO().getTask());
			taskEnt.setStartDate(PMToolUtil.stringToDate(request.getTaskVO().getStartDate()));
			taskEnt.setEndDate(PMToolUtil.stringToDate(request.getTaskVO().getEndDate()));
			taskEnt.setPriority(request.getTaskVO().getPriority());
			taskEnt.setProjectEnt(projectEnt);
			taskEnt.setParentTaskEnt(parentTaskEnt);
			taskEnt.setStatus(request.getTaskVO().getStatus());
			taskRepository.save(taskEnt);
			status = "Success";
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return status;
	}

	@Override
	public String updateParentTask(GetParentTaskRequest request) throws PMToolException {
		// TODO Auto-generated method stub
		String status = EMPTY;
		ParentTaskComp parentTaskEnt = new ParentTaskComp();
		try {
			if(ACTION_DELETE.equalsIgnoreCase(request.getAction())) {
				parentTaskEnt.setParentId(request.getParentTaskVO().getParentId());
				if(deleteTask(request.getParentTaskVO().getParentId())) {
					parentTaskRepository.delete(parentTaskEnt);
				} else {
					logger.error("updateParentTask - Task deletion failed");
					status = USER_DELETE_FAILED_MESSAGE;
				}
			} else {
				ProjectComp projectEnt = projectRepository.findByProjectId(request.getParentTaskVO().getProjectId());
				
				parentTaskEnt.setParentId(request.getParentTaskVO().getParentId());
				parentTaskEnt.setParentTask(request.getParentTaskVO().getParentTaskName());
				parentTaskEnt.setProjectEnt(projectEnt);
				parentTaskRepository.save(parentTaskEnt);
			}
			
			status = "Success";
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return status;
	}
	
	@Override
	public String updateProject(GetProjectRequest request) throws PMToolException {
		// TODO Auto-generated method stub
		String status = EMPTY;
		ProjectComp projectEnt = new ProjectComp();
		try {
			if(ACTION_DELETE.equalsIgnoreCase(request.getAction())) {
				projectEnt.setProjectId(request.getProjectVO().getProjectId());
				if(deleteTask(request.getProjectVO().getProjectId())) {
					if(deleteParentTask(request.getProjectVO().getProjectId())) {
						projectRepository.delete(projectEnt);
					} else {
						logger.error("updateProject - Parent Task deletion failed");
						status = USER_DELETE_FAILED_MESSAGE;
					}
				} else {
					logger.error("updateProject - Task deletion failed");
					status = USER_DELETE_FAILED_MESSAGE;
				}
			} else {
				UserComp userEnt = userRepository.findByUserId(request.getProjectVO().getEmpId());
				
				projectEnt.setProjectId(request.getProjectVO().getProjectId());
				projectEnt.setProject(request.getProjectVO().getProject());
				projectEnt.setPriority(request.getProjectVO().getPriority());
				projectEnt.setStartDate(PMToolUtil.stringToDate(request.getProjectVO().getStartDate()));
				projectEnt.setEndDate(PMToolUtil.stringToDate(request.getProjectVO().getEndDate()));
				projectEnt.setUserEnt(userEnt);
				projectRepository.save(projectEnt);
			}
			status = "Success";
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return status;
	}

	@Override
	public String updateUser(GetUserRequest request) throws PMToolException {
		// TODO Auto-generated method stub
		UserComp userEnt = new UserComp();
		String status = EMPTY;
		try {
      userEnt.setUserId(request.getUserVO().getUserId());
			if(ACTION_DELETE.equalsIgnoreCase(request.getAction())) {
				status = deleteUser(request, userEnt);
			} else {
				userEnt.setFname(request.getUserVO().getFname());
				userEnt.setLname(request.getUserVO().getLname());
				userEnt.setEmpId(request.getUserVO().getEmpId());
				userRepository.save(userEnt);
				status = "Success";
			}
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return status;
	}
	
	private List<TaskComp> getTaskByProjectId(int projectId) throws PMToolException {
		List<TaskComp> taskEntList = null;
		try {
			taskEntList = taskRepository.findByProjectId(projectId);
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return taskEntList;
	}
	
	private boolean deleteTask(int projectId) throws PMToolException {
		boolean flag = false;
		try {
			taskRepository.deleteByProjectId(projectId);
			flag = true;
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return flag;
	}
	
	private boolean deleteParentTask(int projectId) throws PMToolException {
		boolean flag = false;
		try {
			parentTaskRepository.deleteByProjectId(projectId);
			flag = true;
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return flag;
	}
	
	private boolean deleteProject(int userId) throws PMToolException {
		boolean flag = false;
		try {
			projectRepository.deleteByUserId(userId);
			flag = true;
		} catch(Exception e) {
			throw new PMToolException(TECH_ERROR_CODE, TECH_ERROR_MESSAGE, STATUS_500);
		}
		return flag;
	}
	
	private String deleteUser(GetUserRequest request, UserComp userEnt) throws PMToolException {
		String status = "Success";
		ProjectComp project = projectRepository.findByUserId(request.getUserVO().getUserId());
		if(null != project) {
			userEnt.setUserId(request.getUserVO().getUserId());
			if(deleteTask(project.getProjectId())) {
				if(deleteParentTask(project.getProjectId())) {
					if(deleteProject(request.getUserVO().getUserId())) {
						userRepository.delete(userEnt);
					} else {
						logger.error("deleteUser - Project deletion failed");
						status = USER_DELETE_FAILED_MESSAGE;
					}
				} else {
					logger.error("deleteUser - Parent Task deletion failed");
					status = USER_DELETE_FAILED_MESSAGE;
				}
			} else {
				logger.error("deleteUser - Task deletion failed");
				status = USER_DELETE_FAILED_MESSAGE;
			}
		}
		return status;
	}
}
