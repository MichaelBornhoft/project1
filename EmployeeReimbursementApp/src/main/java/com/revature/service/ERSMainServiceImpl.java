package com.revature.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.revature.dao.*;
import com.revature.models.*;

public class ERSMainServiceImpl implements ERSMainService {

	private ERSMainDAO emdao;	
	
	private ERSApprovedDAOImpl approved;
	private ERSDeniedDAOImpl denied;
	private ERSPendingDAOImpl pending;
	
	private static Logger log = Logger.getLogger(ERSMainServiceImpl.class);	

	
	@Override
	public int newTicket(ERSMain ticket) {
		log.info("in service layer. Registering Manager: " + ticket);
		return emdao.insert(ticket);
	}
	
	public void approve(ERSMain ticket) {
		approved.insert(ticket);
		pending.delete(ticket);
	}
	
	public void deny(ERSMain ticket) {
		denied.insert(ticket);
		pending.delete(ticket);
	}

	@Override
	public ERSMain findTicketById(int ticketId) {
		log.info("in service layer. searching Tickets by id: " + ticketId);
		
		if (pending.selectById(ticketId) != null) {
			return pending.selectById(ticketId);
		}else if (approved.selectById(ticketId) != null) {
			return approved.selectById(ticketId);
		}else {
			return denied.selectByemployeeTicketId(ticketId);
		}
	}

	@Override
	public ERSMain findTicketByEmployee_Id(int employeeId) {
		log.info("in service layer. searching for ticket by employee id: " + employeeId);
		
		if (pending.selectById(employeeId) != null) {
			return pending.selectById(employeeId);
		}else if (approved.selectById(employeeId) != null) {
			return approved.selectById(employeeId);
		}else {
			return denied.selectByemployeeTicketId(employeeId);
		}
	}

	@Override
	public boolean editTicketStatus(ERSMain ticket) {
		log.info("in service layer. editing ticket status: " + ticket);
		
		
		return emdao.update(ticket);
	}

	@Override
	public List<ERSMain> finadallTickets() {
		log.info("in service layer. finding all Tickets...");
		List<ERSMain> tickets = new ArrayList<ERSMain>();
		
		tickets.addAll(approved.selectAll());
		tickets.addAll(pending.selectAll());
		tickets.addAll(denied.selectAll());
		
		return tickets.stream().sorted(Comparator.comparingInt(ERSMain::getTicketId)).collect(Collectors.toList());
	}
	
	public List<ERSMain> finadallApproved() {
		log.info("in service layer. finding all Tickets...");
		List<ERSMain> tickets = new ArrayList<ERSMain>();
		
		tickets.addAll(approved.selectAll());
		
		return tickets;
	}
	
	public List<ERSMain> finadallDenied() {
		log.info("in service layer. finding all Tickets...");
		List<ERSMain> tickets = new ArrayList<ERSMain>();
		
		tickets.addAll(denied.selectAll());
		
		return tickets;
	}
	
	public List<ERSMain> finadallPending() {
		log.info("in service layer. finding all Tickets...");
		List<ERSMain> tickets = new ArrayList<ERSMain>();
		
		tickets.addAll(pending.selectAll());
		
		return tickets;
	}


	@Override
	public boolean deleteTicket(ERSMain ticket) {
		log.info("in service layer. removing Ticket: " + ticket);
		return emdao.delete(ticket);
	}

}
