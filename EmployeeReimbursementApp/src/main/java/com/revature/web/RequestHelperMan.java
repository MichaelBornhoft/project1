package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.dao.ManagerDAOImpl;

import com.revature.models.Manager;

import com.revature.service.ManagerService;
import com.revature.service.ManagerServiceImpl;

public class RequestHelperMan {
	
	
private static ManagerService Managerv = new ManagerServiceImpl(new ManagerDAOImpl());
	
	private static Logger log = Logger.getLogger(RequestHelperEmp.class);
	private static ObjectMapper om = new ObjectMapper();
	

	public static void processManagerById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...searching Manager by ID...");
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();

		
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString(); 
		String [] sepByAmp = body.split("&");
		
		List<String> values = new ArrayList<String>();
		
		for (String pair : sepByAmp) { 
			values.add(pair.substring(pair.indexOf("=") + 1)); 
		}
		log.info("Manager attempted to register with information:\n " + body);
		
		// determine what type of search is needed
		if(body.startsWith("ManagerId")) {
			//1. set the content type to return text to the browser
			response.setContentType("application/json");
			
			// 2. Get Manager in the Database by id
			int id = Integer.parseInt(values.get(0));
			Manager Manager = Managerv.findManagerById(id);
			
			// 3. Turn the list of Java Objects into a JSON string (using Jackson Databind Object Mapper).
			String json = om.writeValueAsString(Manager);
			
			// 4. Use a Print Writer to write the objects to the response body seen in the browser
			PrintWriter out = response.getWriter();
			out.println(json);
		}
	}
	
	
	public static void processNewManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...process new Manager...");
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString(); 
		String [] sepByAmp = body.split("&"); 
		
		List<String> values = new ArrayList<String>();
		List<Integer> ovalues = new ArrayList<Integer>();
		
		for (String pair : sepByAmp) { 
			values.add(pair.substring(pair.indexOf("=") + 1)); 
		}
		log.info("Ticket attempted to register with information:\n " + body);
		// capture the actual Employeename and password values
		int managerId = ovalues.get(0);
		String managerName = values.get(1);
		String managerPassword = values.get(2);
		String managerFirstName = values.get(3); 
		String managerLastName = values.get(4); 
		
		
		
		Manager m = new Manager(managerId, managerName, managerPassword, managerFirstName, managerLastName);
		int targetId = Managerv.register(m);

		if (targetId != 0) {
			PrintWriter pw = response.getWriter();
			m.setManagerId(targetId);
			log.info("New Manager: " + m);
			String json = om.writeValueAsString(m);
			pw.println(json);
			System.out.println("JSON:\n" + json);
			
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
			log.info("Manager has successfully been created.");
		} else {
			response.setContentType("application/json");
			response.setStatus(204); 
		}
		log.info("leaving request helper now...");
	}
	
	
	public static void processManagerUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...processManagerUpdate...");
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString(); 
		String [] sepByAmp = body.split("&"); 
		
		List<String> values = new ArrayList<String>();
		List<Integer> ovalues = new ArrayList<Integer>(); 
		for (String pair : sepByAmp) { 
			values.add(pair.substring(pair.indexOf("=") + 1)); 
		}
		log.info("Manager attempted to update with information:\n " + body);
		
		int managerId = ovalues.get(0);
		String managerName = values.get(1);
		String managerPassword = values.get(2);
		String managerFirstName = values.get(3); 
		String managerLastName = values.get(4); 
		
		
		Manager tempManager = new Manager(); 
		tempManager.setManagerId(managerId);
		tempManager.setManagername(managerName);
		tempManager.setPassword(managerPassword);
		tempManager.setFirstName(managerFirstName);
		tempManager.setLastName(managerLastName);
		
		
		boolean isUpdated = Managerv.editManager(tempManager);

		if (isUpdated) {
			PrintWriter pw = response.getWriter();
			log.info("Edit successful! New Manager info: " + tempManager);
			String json = om.writeValueAsString(tempManager);
			pw.println(json);
			System.out.println("JSON:\n" + json);
			
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
			log.info("Manager has successfully been edited.");
		} else {
			response.setContentType("application/json");
			response.setStatus(400); // this means that the connection was successful but no Manager was updated!
		}
		log.info("leaving request helper now...");
	}


	

	public static void processError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// if something goes wrong, redirect the Manager to a custom 404 error page
		req.getRequestDispatcher("error.html").forward(req, resp);
	        /*
		 * Remember that the forward() method does NOT produce a new request,
		 * it just forwards it to a new resource, and we also maintain the URL
		*/
	}

	
	
	
	
	
	

}
