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
import com.revature.dao.EmployeeDAOImpl;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;

public class RequestHelper {

	private static EmployeeService Employeev = new EmployeeServiceImpl(new EmployeeDAOImpl());
	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("in requesthelper. getting Employees....");
		// 1. set the content type to return JSON to the browser
		resp.setContentType("application/json");
		
		// 2. get a list of all Employees in the database
		List<Employee> allEmployees = Employeev.findAllEmployees();
		// 3. turn that list of java objects into a JSON string (using Jackson)
		String json = om.writeValueAsString(allEmployees);
		
		// 4. use a PrintWriter to write the objects to the response body which will be seen in the browser
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		log.info("leaving requesthelper");
	}

	public static void processEmployeeBySearchParam(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...searching Employee by param...");
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
		
		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value -> [bob, pass]
		}
		log.info("Employee attempted to register with information:\n " + body);
		
		// determine what type of search is needed
		if(body.startsWith("id")) {
			//1. set the content type to return text to the browser
			response.setContentType("application/json");
			
			// 2. Get Employee in the Database by id
			int id = Integer.parseInt(values.get(0));
			Employee Employee = Employeev.findEmployeeById(id);
			
			// 3. Turn the list of Java Objects into a JSON string (using Jackson Databind Object Mapper).
			String json = om.writeValueAsString(Employee);
			
			// 4. Use a Print Writer to write the objects to the response body seen in the browser
			PrintWriter out = response.getWriter();
			out.println(json);
		}else if(body.startsWith("firstname")) {
			//1. set the content type to return text to the browser
			response.setContentType("application/json");
			
			// 2. Get Employee in the Database by firstname
			String firstname = values.get(0);
			Employee Employee = Employeev.findEmployeeByFirstName(firstname);
			
			// 3. Turn the list of Java Objects into a JSON string (using Jackson Databind Object Mapper).
			String json = om.writeValueAsString(Employee);
			
			// 4. Use a Print Writer to write the objects to the response body seen in the browser
			PrintWriter out = response.getWriter();
			
			out.println(json);
		}
	}

	public static void processRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...processRegistration...");
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString(); 
		String [] sepByAmp = body.split("&"); // separate Employeename=bob&password=pass -> [Employeename=bob, password=pass]
		
		List<String> values = new ArrayList<String>();
		
		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value -> [bob, pass]
		}
		log.info("Employee attempted to register with information:\n " + body);
		// capture the actual Employeename and password values
		String Employeename = values.get(0); // bob
		String password = values.get(1); // pass
		String firstname = values.get(2);
		String lastname = values.get(3);
		
		Employee u = new Employee(Employeename, password, firstname, lastname);
		int targetId = Employeev.register(u);

		if (targetId != 0) {
			PrintWriter pw = response.getWriter();
			u.setId(targetId);
			log.info("New Employee: " + u);
			String json = om.writeValueAsString(u);
			pw.println(json);
			System.out.println("JSON:\n" + json);
			
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
			log.info("Employee has successfully been created.");
		} else {
			response.setContentType("application/json");
			response.setStatus(204); // this means that the connection was successful but no Employee created!
		}
		log.info("leaving request helper now...");
	}

	public static void processError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// if something goes wrong, redirect the Employee to a custom 404 error page
		req.getRequestDispatcher("error.html").forward(req, resp);
	        /*
		 * Remember that the forward() method does NOT produce a new request,
		 * it just forwards it to a new resource, and we also maintain the URL
		*/
	}

	public static void processEmployeeUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...processEmployeeUpdate...");
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString(); 
		String [] sepByAmp = body.split("&"); // separate Employeename=bob&password=pass -> [Employeename=bob, password=pass]
		
		List<String> values = new ArrayList<String>();
		
		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value -> [bob, pass]
		}
		log.info("Employee attempted to update with information:\n " + body);
		// capture the actual Employeename and password values
		int id = Integer.parseInt(values.get(0)); //id numbers cannot be modifed!
		String Employeename = values.get(1); // bob
		String password = values.get(2); // pass
		String firstname = values.get(3);
		String lastname = values.get(4);
		
		Employee tempEmployee = new Employee();
		tempEmployee.setId(id);
		tempEmployee.setEmployeename(Employeename);
		tempEmployee.setPassword(password);
		tempEmployee.setFirstName(firstname);
		tempEmployee.setLastName(lastname);
		boolean isUpdated = Employeev.editEmployee(tempEmployee);

		if (isUpdated) {
			PrintWriter pw = response.getWriter();
			log.info("Edit successful! New Employee info: " + tempEmployee);
			String json = om.writeValueAsString(tempEmployee);
			pw.println(json);
			System.out.println("JSON:\n" + json);
			
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
			log.info("Employee has successfully been edited.");
		} else {
			response.setContentType("application/json");
			response.setStatus(400); // this means that the connection was successful but no Employee was updated!
		}
		log.info("leaving request helper now...");
	}

	public static void processEmployeeDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...processEmployeeUpdate...");
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString(); 
		String [] sepByAmp = body.split("&"); // separate Employeename=bob&password=pass -> [Employeename=bob, password=pass]
		
		List<String> values = new ArrayList<String>();
		
		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value -> [bob, pass]
		}
		log.info("Employee attempted to update with information:\n " + body);
		// capture the actual Employeename and password values
		int id = Integer.parseInt(values.get(0)); //id numbers cannot be modifed!
		String Employeename = values.get(1); // bob
		String password = values.get(2); // pass
		String firstname = values.get(3);
		String lastname = values.get(4);
		
		Employee tempEmployee = new Employee();
		tempEmployee.setId(id);
		tempEmployee.setEmployeename(Employeename);
		tempEmployee.setPassword(password);
		tempEmployee.setFirstName(firstname);
		tempEmployee.setLastName(lastname);
		boolean isDeleted = Employeev.deleteEmployee(tempEmployee);

		if (isDeleted) {
			PrintWriter pw = response.getWriter();
			log.info("Delete successful! Removed Employee: " + tempEmployee);
			String json = om.writeValueAsString(tempEmployee);
			pw.println(json);
			System.out.println("JSON:\n" + json);
			
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
			log.info("Employee has successfully been edited.");
		} else {
			response.setContentType("application/json");
			response.setStatus(400); // this means that the connection was successful but no Employee was updated!
		}
		log.info("leaving request helper now...");
		
	}}