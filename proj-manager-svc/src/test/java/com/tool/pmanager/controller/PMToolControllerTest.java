package com.tool.pmanager.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.tool.pmanager.PMToolBootApplication;
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

@SpringBootTest(classes = PMToolBootApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PMToolControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PMToolService service;

	@Test
	public void test_getUser_endpoint() throws Exception {
		given(service.getUser()).willReturn(getMockUser());
		this.mockMvc
				.perform(get("/getUser").contentType(MediaType.APPLICATION_JSON).header("Accept", "*/*"))
				.andExpect(status().isOk());
	}

	

	
	@Test
	public void test_update_addUser_endpoint() throws Exception {
		given(service.updateUser(getMockUser_forAdd())).willReturn("Success");
		this.mockMvc.perform(post("/updateUser").contentType(MediaType.APPLICATION_JSON)
				.content(createModifyUserJson())).andExpect(status().isOk());
	}
	
	@Test
	public void test_getProject_endpoint() throws Exception {
		given(service.getProject()).willReturn(getMockProject());
		this.mockMvc.perform(get("/getProject").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void test_getTask_endpoint() throws Exception {
		given(service.viewTask(1)).willReturn(getMockTask());
		this.mockMvc.perform(
				post("/viewTask").contentType(MediaType.APPLICATION_JSON).content(createTaskIdJson()))
				.andExpect(status().isOk());
	}
	@Test
	public void test_getProjectexception() throws Exception {
		given(service.getProject()).willThrow(new PMToolException("", "", 500));
		this.mockMvc.perform(get("/getProject").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_XML)).andExpect(status().is5xxServerError());
	}
	
	@Test
	public void test_update_addProject_endpoint() throws Exception {
		given(service.updateProject(getMockProject_forAdd())).willReturn("Success");
		this.mockMvc.perform(post("/updateProject").contentType(MediaType.APPLICATION_JSON)
				.content(createModifyProjectJson())).andExpect(status().isOk());
	} 

	@Test
	public void test_update_addParentTask_endpoint() throws Exception {
		given(service.updateParentTask(getMockParentTask_forAdd())).willReturn("Success");
		this.mockMvc.perform(post("/updateParentTask").contentType(MediaType.APPLICATION_JSON)
				.content(createModifyParentTaskJson())).andExpect(status().isOk());
	}

	@Test
	public void test_update_addTask_endpoint() throws Exception {
		given(service.updateTask(getMockTask_forAdd())).willReturn("Success");
		this.mockMvc.perform(post("/updateTask").contentType(MediaType.APPLICATION_JSON)
				.content(createModifyTaskJson())).andExpect(status().isOk());
	}

	@Test
	public void test_getUserexception() throws Exception {
		given(service.getUser()).willThrow(new PMToolException("", "", 500));
		this.mockMvc.perform(get("/getUser").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_XML)).andExpect(status().is5xxServerError());
	}

	
	@Test
	public void test_getParentTask_endpoint() throws Exception {
		given(service.getParentTask()).willReturn(getMockParentTask());
		this.mockMvc.perform(get("/getParentTask").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	

	@Test
	public void test_getParentTaskexception() throws Exception {
		given(service.getParentTask()).willThrow(new PMToolException("", "", 500));
		this.mockMvc.perform(get("/getParentTask").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_XML)).andExpect(status().is5xxServerError());
	}

	@Test
	public void test_getTaskexception() throws Exception {
		given(service.viewTask(1)).willThrow(new PMToolException("", "", 500));
		this.mockMvc
				.perform(post("/viewTask").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_XML).content(createTaskIdJson()))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void test_updateUserexception() throws Exception {
		given(service.updateUser(any(GetUserRequest.class))).willThrow(new PMToolException("", "", 500));
		this.mockMvc
				.perform(post("/updateUser").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_XML).content(createModifyUserJson()))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void test_updateProjectexception() throws Exception {
		given(service.updateProject(any(GetProjectRequest.class))).willThrow(new PMToolException("", "", 500));
		this.mockMvc
				.perform(post("/updateProject").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_XML).content(createModifyProjectJson()))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void test_updateParentTaskexception() throws Exception {
		given(service.updateParentTask(any(GetParentTaskRequest.class)))
				.willThrow(new PMToolException("", "", 500));
		this.mockMvc
				.perform(post("/updateParentTask").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_XML).content(createModifyParentTaskJson()))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void test_updateTaskexception() throws Exception {
		given(service.updateTask(any(GetTaskRequest.class))).willThrow(new PMToolException("", "", 500));
		this.mockMvc
				.perform(post("/updateTask").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_XML).content(createModifyTaskJson()))
				.andExpect(status().is5xxServerError());
	}

	
	private GetUserResponse getMockUser() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetUserResponse res = new GetUserResponse();
		res = gson.fromJson(new FileReader("mockData/getUserRes.json"), GetUserResponse.class);
		return res;
	}

	private GetProjectResponse getMockProject() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetProjectResponse res = new GetProjectResponse();
		res = gson.fromJson(new FileReader("mockData/getProjectRes.json"), GetProjectResponse.class);
		return res;
	}

	private GetParentTaskResponse getMockParentTask()
			throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetParentTaskResponse res = new GetParentTaskResponse();
		res = gson.fromJson(new FileReader("mockData/getParentTaskRes.json"), GetParentTaskResponse.class);
		return res;
	}

	private GetTaskResponse getMockTask() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetTaskResponse res = new GetTaskResponse();
		res = gson.fromJson(new FileReader("mockData/getTaskRes.json"), GetTaskResponse.class);
		return res;
	}

	private GetUserRequest getMockUser_forAdd() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetUserRequest req = new GetUserRequest();
		req = gson.fromJson(new FileReader("mockData/addUserReq.json"), GetUserRequest.class);
		return req;
	}

	private GetProjectRequest getMockProject_forAdd()
			throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetProjectRequest req = new GetProjectRequest();
		req = gson.fromJson(new FileReader("mockData/addProjectReq.json"), GetProjectRequest.class);
		return req;
	}

	private GetParentTaskRequest getMockParentTask_forAdd()
			throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetParentTaskRequest req = new GetParentTaskRequest();
		req = gson.fromJson(new FileReader("mockData/addParentTaskReq.json"), GetParentTaskRequest.class);
		return req;
	}

	private GetTaskRequest getMockTask_forAdd() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		GetTaskRequest req = new GetTaskRequest();
		req = gson.fromJson(new FileReader("mockData/addTaskReq.json"), GetTaskRequest.class);
		return req;
	}

	
	private static String createTaskIdJson() {
		return "{\"taskVO\" : {\"projectId\" : 1}}";
	}

	private String createModifyUserJson() throws Exception {
		String content = "";
		BufferedReader reader = new BufferedReader(new FileReader("mockData/addUserReq.json"));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		content = stringBuilder.toString();
		return content;
	}

	private String createModifyProjectJson() throws Exception {
		String content = "";
		BufferedReader reader = new BufferedReader(new FileReader("mockData/addProjectReq.json"));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		content = stringBuilder.toString();
		return content;
	}

	private String createModifyParentTaskJson() throws Exception {
		String content = "";
		BufferedReader reader = new BufferedReader(new FileReader("mockData/addParentTaskReq.json"));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		content = stringBuilder.toString();
		return content;
	}

	private String createModifyTaskJson() throws Exception {
		String content = "";
		BufferedReader reader = new BufferedReader(new FileReader("mockData/addTaskReq.json"));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		content = stringBuilder.toString();
		return content;
	}

}
