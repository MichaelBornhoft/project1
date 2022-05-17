package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.*;
import com.revature.models.*;

public class ERSMainServiceImpl implements ERSMainService {

	private ERSMainDAO emdao;	
	
	private ERSApprovedDao approved;
	private ERSDeniedDao denied;
	
	private static Logger log = Logger.getLogger(ERSMainServiceImpl.class);	
	
	
	public ERSMainServiceImpl(ERSMainDAOImpl dao) {
		super();
		this.emdao = dao; 
	}
	
	
	@Override
	public int newTicket(ERSMain ticket) {
		log.info("in service layer. Registering Manager: " + ticket);
		return emdao.insert(ticket);
	}

	@Override
	public ERSMain findTicketById(int ticketId) {
		log.info("in service layer. searching Tickets by id: " + ticketId);
		
		if (emdao.selectById(ticketId) != null) {
			return emdao.selectById(ticketId);
		}else if (approved.selectById(ticketId) != null) {
			return ;
		}else
		return emdao.selectById(ticketId);
	}

	@Override
	public ERSMain findTicketByEmployee_Id(int employeeId) {
		log.info("in service layer. searching for ticket by employee id: " + employeeId);
		return emdao.selectById(employeeId);
	}

	@Override
	public boolean editTicketStatus(ERSMain ticket) {
		log.info("in service layer. editing ticket status: " + ticket);
		return emdao.update(ticket);
	}

	@Override
	public List<ERSMain> finadallTickets() {
		log.info("in service layer. finding all Tickets...");
		return emdao.selectAll();
	}


	@Override
	public boolean deleteTicket(ERSMain ticket) {
		log.info("in service layer. removing Ticket: " + ticket);
		return emdao.delete(ticket);
	}

}
