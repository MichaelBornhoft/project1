package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class EmployeeReimburseAppERS extends HttpServlet {
	
	
	private static final long serialVersionUID = 8339100247721381693L;

	private static Logger log = Logger.getLogger(EmployeeReimburseAppEmp.class);
	
	//GET methods are used to retrieve resources from an API 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//save the URI and rewrite it to determine what functionality the Employee is requesting based on that endpoint
		final String URI = req.getRequestURI().replace("/EmployeeReimburseAppERS/", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		case "Tickets": // query the DB and return a list of all Employees
			log.info("getting Employee list...");
			RequestHelperERS.processAllTickets(req, resp);
			break;
		case "Ticket":
			log.info("search Employee by name or id. URI: " + URI);
			RequestHelperERS.processTicketById(req, resp);
			break;
		case "TicketByStatus":
			log.info("getting Tickets by status. . .");
			RequestHelperERS.processTicketByStatus(req, resp); 
			break; 
		case "TicketByemployeeId":
			log.info("getting ticket by employeeId. . .");
			RequestHelperERS.processTicketByEmployeeId(req, resp);
		default:
			log.info("showing error message...");
			RequestHelperEmp.processError(req, resp);
			break;
		}
	}
	
	//POST methods are used for creating resources in your API
	//instead of passing data within the URL, it safely passes that info only in the body
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//save the URI and rewrite it to determine what functionality the Employee is requesting based on that endpoint
		final String URI = req.getRequestURI().replace("/EmployeeReimburseAppERS/", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		case "registerTicket": 
			log.info("registering new Ticket...");
			RequestHelperERS.processNewTicket(req, resp);
			break;
		default:
			log.info("showing error message...");
			RequestHelperEmp.processError(req, resp);
			break;
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//save the URI and rewrite it to determine what functionality the Employee is requesting based on that endpoint
		final String URI = req.getRequestURI().replace("/EmployeeReimburseAppERS/", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		case "updateTicket": 
			log.info("updating Employee...");
			RequestHelperERS.processTicketUpdate(req, resp);
			break;
		default:
			log.info("showing error message...");
			RequestHelperEmp.processError(req, resp);
			break;
		}
	}
	

}
