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
import com.revature.dao.ERSMainDAOImpl;

import com.revature.models.ERSMain;

import com.revature.service.ERSMainService;
import com.revature.service.ERSMainServiceImpl;


public class RequestHelperERS {
	
	

	 static ERSMainService ERSMainv = new ERSMainServiceImpl(new ERSMainDAOImpl());
	private static Logger log = Logger.getLogger(RequestHelperEmp.class);
	private static ObjectMapper om = new ObjectMapper();
	
	
	
	
	public static void processAllTickets(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("in requesthelper. getting all tickets....");
		// 1. set the content type to return JSON to the browser
		resp.setContentType("application/json");
		
		// 2. get a list of all tickets in the database
		List<ERSMain> allTickets = ERSMainv.finadallTickets();
		// 3. turn that list of java objects into a JSON string (using Jackson)
		String json = om.writeValueAsString(allTickets);
		
		// 4. use a PrintWriter to write the objects to the response body which will be seen in the browser
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		log.info("leaving requesthelper");
	}
	
	
	
	
	public static void processTicketById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...searching Ticket by ID...");
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
		log.info("Ticket attempted to register with information:\n " + body);
		
		// determine what type of search is needed
		if(body.startsWith("ticketId")) {
			//1. set the content type to return text to the browser
			response.setContentType("application/json");
			
			// 2. Get Employee in the Database by id
			int id = Integer.parseInt(values.get(0));
			ERSMain ERSMain = ERSMainv.findTicketById(id);
			
			// 3. Turn the list of Java Objects into a JSON string (using Jackson Databind Object Mapper).
			String json = om.writeValueAsString(ERSMain);
			
			// 4. Use a Print Writer to write the objects to the response body seen in the browser
			PrintWriter out = response.getWriter();
			out.println(json);
		}
	}
	
	public static void processNewTicket(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...process new ticket...");
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
		List<Integer> ovalues = new ArrayList<Integer>();
		
		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value -> [bob, pass]
		}
		log.info("Ticket attempted to register with information:\n " + body);
		// capture the actual Employeename and password values
		int ticketId = ovalues.get(0);
		int employeeTicketId = ovalues.get(1);
		int managerTicketId = ovalues.get(2);
		String ticketName = values.get(3); // bob
		String description = values.get(4); // pass
		double ticketAmount = ovalues.get(5);
		String ticketStatus = values.get(6);
		
		ERSMain t = new ERSMain(ticketId, employeeTicketId, managerTicketId, ticketName, description, ticketAmount, ticketStatus);
		int targetId = ERSMainv.newTicket(t);

		if (targetId != 0) {
			PrintWriter pw = response.getWriter();
			t.setTicketId(targetId);
			log.info("New Ticket: " + t);
			String json = om.writeValueAsString(t);
			pw.println(json);
			System.out.println("JSON:\n" + json);
			
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
			log.info("Ticket has successfully been created.");
		} else {
			response.setContentType("application/json");
			response.setStatus(204); // this means that the connection was successful but no Ticket created!
		}
		log.info("leaving request helper now...");
	}
	
	
	

	public static void processTicketUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...processTicketUpdate...");
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString(); 
		String [] sepByAmp = body.split("&"); // separate Ticketname=bob&password=pass -> [Ticketname=bob, password=pass]
		
		List<String> values = new ArrayList<String>();
		List<Integer> ovalues = new ArrayList<Integer>();
		
		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value -> [bob, pass]
		}
		log.info("Ticket attempted to update with information:\n " + body);
		// capture the actual Ticketname and password values
		//int id = Integer.parseInt(values.get(0)); //id numbers cannot be modifed!
		int ticketId = ovalues.get(0);
		int employeeTicketId = ovalues.get(1);
		int managerTicketId = ovalues.get(2);
		String ticketName = values.get(3); // bob
		String description = values.get(4); // pass
		double ticketAmount = ovalues.get(5);
		String ticketStatus = values.get(6);
		
		ERSMain tempERSMain = new ERSMain();
		tempERSMain.setTicketId(ticketId);
		tempERSMain.getemployeeTicketId(employeeTicketId); 
		tempERSMain.getmanagerTicketId(managerTicketId);
		tempERSMain.setTicketName(ticketName);
		tempERSMain.setDescription(description);
		tempERSMain.setTicketAmount(ticketAmount);
		tempERSMain.setTicketStatus(ticketStatus);
		boolean isUpdated = ERSMainv.editTicketStatus(tempERSMain);

		if (isUpdated) {
			PrintWriter pw = response.getWriter();
			log.info("Edit successful! New Ticket info: " + tempERSMain);
			String json = om.writeValueAsString(tempERSMain);
			pw.println(json);
			System.out.println("JSON:\n" + json);
			
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
			log.info("Ticket has successfully been edited.");
		} else {
			response.setContentType("application/json");
			response.setStatus(400); // this means that the connection was successful but no Ticket was updated!
		}
		log.info("leaving request helper now...");
	}
	
	
	public static void processTicketByEmployeeId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...searching for ticket by Employee ID...");
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
		if(body.startsWith("SELECT * FROM ERS_Ticket WHERE employee_ticket_id")) {
			//1. set the content type to return text to the browser
			response.setContentType("application/json");
			
		
			int id = Integer.parseInt(values.get(0));
			ERSMain ERSMain = ERSMainv.findTicketByEmployee_Id(id);
			
			
			// 3. Turn the list of Java Objects into a JSON string (using Jackson Databind Object Mapper).
			String json = om.writeValueAsString(ERSMain);
			
			// 4. Use a Print Writer to write the objects to the response body seen in the browser
			PrintWriter out = response.getWriter();
			out.println(json);}
		}
	
	public static void processError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// if something goes wrong, redirect the Ticket to a custom 404 error page
		req.getRequestDispatcher("error.html").forward(req, resp);
	        /*
		 * Remember that the forward() method does NOT produce a new request,
		 * it just forwards it to a new resource, and we also maintain the URL
		*/
	}
	

}
