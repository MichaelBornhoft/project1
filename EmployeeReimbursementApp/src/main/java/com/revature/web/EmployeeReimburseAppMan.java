package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class EmployeeReimburseAppMan extends HttpServlet {
	
	
	private static final long serialVersionUID = 8339100247721381693L;

	private static Logger log = Logger.getLogger(EmployeeReimburseAppEmp.class);
	
	//GET methods are used to retrieve resources from an API 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//save the URI and rewrite it to determine what functionality the Employee is requesting based on that endpoint
		final String URI = req.getRequestURI().replace("/EmployeeReimburseAppMan/", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		case "Manager":
			log.info("search Manager by id. URI: " + URI);
			RequestHelperMan.processManagerById(req, resp);
			break;
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
		final String URI = req.getRequestURI().replace("/EmployeeReimburseAppMan/", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		case "registerManager": 
			log.info("registering new Manager...");
			RequestHelperMan.processNewManager(req, resp);
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
		final String URI = req.getRequestURI().replace("/EmployeeReimburseAppMan/", "");
		log.info("URI: " + URI);
		
		switch (URI) {
		case "updateManager": 
			log.info("updating Manager...");
			RequestHelperMan.processManagerUpdate(req, resp);
			break;
		default:
			log.info("showing error message...");
			RequestHelperEmp.processError(req, resp);
			break;
		}
	}
	

}
