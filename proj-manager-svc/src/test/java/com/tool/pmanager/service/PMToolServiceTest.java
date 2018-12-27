package com.tool.pmanager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.tool.pmanager.component.ParentTaskComp;
import com.tool.pmanager.component.ProjectComp;
import com.tool.pmanager.component.TaskComp;
import com.tool.pmanager.component.UserComp;
import com.tool.pmanager.repository.ParentTaskRepository;
import com.tool.pmanager.repository.ProjectRepository;
import com.tool.pmanager.repository.TaskRepository;
import com.tool.pmanager.repository.UserRepository;
import com.tool.pmanager.request.GetParentTaskRequest;
import com.tool.pmanager.request.GetProjectRequest;
import com.tool.pmanager.request.GetTaskRequest;
import com.tool.pmanager.request.GetUserRequest;
import com.tool.pmanager.service.PMToolService;
import com.tool.pmanager.service.PMToolServiceImpl;

@RunWith(SpringRunner.class)
public class PMToolServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String SUCCESS = "Success";
	
	@MockBean
	private ProjectRepository projectRepository;
	
	@MockBean
	private ParentTaskRepository parentTaskRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	
	@MockBean
	private TaskRepository taskRepository;
	
	@Autowired
    private PMToolService projectManagerService;
	
	@Configuration
	static class ProjectManagerServiceTestContextConfiguration {
		@Bean
		public PMToolService projectManagerService() {
			return new PMToolServiceImpl();
		}
	}

	@Before
    public void setUp() throws Exception {
		given(userRepository.findAll()).willReturn(getMockUser());
		given(taskRepository.findByProjectId(1)).willReturn(getMockTask());
		given(projectRepository.findAll()).willReturn(getMockProject());
		given(parentTaskRepository.findAll()).willReturn(getMockParentTask());
		given(projectRepository.findByUserId(1)).willReturn(getMockProjEnt());
	}
	
	@Test
	public void test_getUserService() throws Exception {
		assertNotNull(projectManagerService.getUser());
	}
	
	@Test
	public void test_getTaskService() throws Exception {
		assertNotNull(projectManagerService.viewTask(1));
	}
	
	@Test
	public void test_getProjectService() throws Exception {
		assertNotNull(projectManagerService.getProject());
	}
	
	@Test
	public void test_getParentTaskService() throws Exception {
		assertNotNull(projectManagerService.getParentTask());
	}
	
	
	@Test
	public void test_update_addParentTaskService() throws Exception {
		assertNotNull(projectManagerService.updateParentTask(getMockParentTask_forAdd()));
		assertEquals(SUCCESS, projectManagerService.updateParentTask(getMockParentTask_forAdd()));
	}
	
	@Test
	public void test_update_addTaskService() throws Exception {
		assertNotNull(projectManagerService.updateTask(getMockTask_forAdd()));
		assertEquals(SUCCESS, projectManagerService.updateTask(getMockTask_forAdd()));
	}
	
	@Test
	public void test_update_addUserService() throws Exception {
		assertNotNull(projectManagerService.updateUser(getMockUser_forAdd()));
		assertEquals("Success", projectManagerService.updateUser(getMockUser_forAdd()));
	}
	
	@Test
	public void test_update_addProjectService() throws Exception {
		assertNotNull(projectManagerService.updateProject(getMockProject_forAdd()));
		assertEquals(SUCCESS, projectManagerService.updateProject(getMockProject_forAdd()));
	}
	
	
	@Test
	public void test_update_deleteUserService() throws Exception {
		assertNotNull(projectManagerService.updateUser(getMockUser_forDelete()));
		assertEquals("Success", projectManagerService.updateUser(getMockUser_forDelete()));
	}
	
	@Test
	public void test_update_deleteProjectService() throws Exception {
		assertNotNull(projectManagerService.updateProject(getMockProject_forDelete()));
		assertEquals(SUCCESS, projectManagerService.updateProject(getMockProject_forDelete()));
	}
	
	@Test
	public void test_update_deleteParentTaskService() throws Exception {
		assertNotNull(projectManagerService.updateParentTask(getMockParentTask_forDelete()));
		assertEquals(SUCCESS, projectManagerService.updateParentTask(getMockParentTask_forDelete()));
	}
	
	@Test
	public void test_update_deleteTaskService() throws Exception {
		assertNotNull(projectManagerService.updateTask(getMockTask_forDelete()));
		assertEquals(SUCCESS, projectManagerService.updateTask(getMockTask_forDelete()));
	}
	
	private List<UserComp> getMockUser() {
		Gson gson = new Gson();
		List<UserComp> res = new ArrayList<UserComp>();
		try {
			UserComp ent = gson.fromJson(new FileReader("mockData/getUserRes.json"), UserComp.class);
			res.add(ent);
        } catch (Exception e) {
            logger.error("Exception occured inProjectManagerServiceTest getMockUser : " + e);
        }
		return res;
	}

	private List<ParentTaskComp> getMockParentTask() {
		Gson gson = new Gson();
		List<ParentTaskComp> res = new ArrayList<ParentTaskComp>();
		try {
			ParentTaskComp ent = gson.fromJson(new FileReader("mockData/getParentTaskRes.json"), ParentTaskComp.class);
			res.add(ent);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockParentTask : " + e);
        }
		return res;
	}
	
	private List<ProjectComp> getMockProject() {
		Gson gson = new Gson();
		List<ProjectComp> res = new ArrayList<ProjectComp>();
		try {
			ProjectComp ent = gson.fromJson(new FileReader("mockData/getProjectRes.json"), ProjectComp.class);
			res.add(ent);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockProject : " + e);
        }
		return res;
	}
	
	
	
	private List<TaskComp> getMockTask() {
		Gson gson = new Gson();
		List<TaskComp> res = new ArrayList<TaskComp>();
		try {
			TaskComp ent = gson.fromJson(new FileReader("mockData/getTaskEntRes.json"), TaskComp.class);
			res.add(ent);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockTask : " + e);
        }
		return res;
	}
	
	private GetUserRequest getMockUser_forAdd() {
		Gson gson = new Gson();
		GetUserRequest req = new GetUserRequest();
		try {
			req = gson.fromJson(new FileReader("mockData/addUserReq.json"), GetUserRequest.class);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockUser_forAdd : " + e);
        }
		return req;
	}
	
	private GetProjectRequest getMockProject_forAdd() {
		Gson gson = new Gson();
		GetProjectRequest req = new GetProjectRequest();
		try {
			req = gson.fromJson(new FileReader("mockData/addProjectReq.json"), GetProjectRequest.class);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockProject_forAdd : " + e);
        }
		return req;
	}
	
	private ProjectComp getMockProjEnt() {
		Gson gson = new Gson();
		ProjectComp res = new ProjectComp();
		try {
			res = gson.fromJson(new FileReader("mockData/projectEntRes.json"), ProjectComp.class);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockTask : " + e);
        }
		return res;
	}
	
	private GetParentTaskRequest getMockParentTask_forAdd() {
		Gson gson = new Gson();
		GetParentTaskRequest req = new GetParentTaskRequest();
		try {
			req = gson.fromJson(new FileReader("mockData/addParentTaskReq.json"), GetParentTaskRequest.class);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockParentTask_forAdd : " + e);
        }
		return req;
	}
	
	private GetTaskRequest getMockTask_forAdd() {
		Gson gson = new Gson();
		GetTaskRequest req = new GetTaskRequest();
		try {
			req = gson.fromJson(new FileReader("mockData/addTaskReq.json"), GetTaskRequest.class);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockTask_forAdd : " + e);
        }
		return req;
	}
	
	private GetUserRequest getMockUser_forDelete() {
		Gson gson = new Gson();
		GetUserRequest req = new GetUserRequest();
		try {
			req = gson.fromJson(new FileReader("mockData/deleteUserReq.json"), GetUserRequest.class);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockUser_forAdd : " + e);
        }
		return req;
	}
	
	private GetProjectRequest getMockProject_forDelete() {
		Gson gson = new Gson();
		GetProjectRequest req = new GetProjectRequest();
		try {
			req = gson.fromJson(new FileReader("mockData/deleteProjectReq.json"), GetProjectRequest.class);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockProject_forAdd : " + e);
        }
		return req;
	} 
	
	private GetParentTaskRequest getMockParentTask_forDelete() {
		Gson gson = new Gson();
		GetParentTaskRequest req = new GetParentTaskRequest();
		try {
			req = gson.fromJson(new FileReader("mockData/deleteParentTaskReq.json"), GetParentTaskRequest.class);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockParentTask_forAdd : " + e);
        }
		return req;
	}
	
	private GetTaskRequest getMockTask_forDelete() {
		Gson gson = new Gson();
		GetTaskRequest req = new GetTaskRequest();
		try {
			req = gson.fromJson(new FileReader("mockData/deleteTaskReq.json"), GetTaskRequest.class);
        } catch (Exception e) {
        	logger.error("Exception occured inProjectManagerServiceTest getMockTask_forAdd : " + e);
        }
		return req;
	}
	
}
