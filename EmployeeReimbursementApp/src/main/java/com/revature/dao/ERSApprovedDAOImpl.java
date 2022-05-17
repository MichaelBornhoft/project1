package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.ERSApproved;
import com.revature.models.ERSMain;
import com.revature.util.HibernateUtil;

public class ERSApprovedDAOImpl implements ERSMainDAO {
	private static Logger log = Logger.getLogger(ERSApprovedDAOImpl.class);

	@Override
	public int insert(ERSMain ticket) {
		log.info("adding ticket to database. ticket info: " + ticket);

		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		int pk = (int) ses.save(ticket);

		tx.commit();

		log.info("insert successful! New ticket id is " + pk);
		return pk; // return the auto-generated pk
	}
	

	public int insertToApproved(ERSMain ticket) {
		log.info("adding ticket to database. ticket info: " + ticket);

		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		int pk = (int) ses.save(ticket);

		tx.commit();

		log.info("insert successful! New ticket id is " + pk);
		return pk; // return the auto-generated pk
	}

	@Override
	public ERSMain selectById(int ticketId) {
		log.info("searching ticket by ticket_id: " + ticketId);

		Session ses = HibernateUtil.getSession();

		ERSMain ticket = (ERSMain) ses
				.createNativeQuery("SELECT * FROM ERS_Approved WHERE Approved_ticket_id = " + ticketId + "", ERSMain.class)
				.getSingleResult();

		log.info("Search complete! Found: " + ticket);

		return ticket;
	}

	@Override
	public List<ERSMain>  selectByemployeeTicketId(int employeeId) {
		log.info("searching ticket by employee_ticket_id: " + employeeId);

		Session ses = HibernateUtil.getSession();

		List<ERSMain> tickets = ses
				.createNativeQuery("SELECT * FROM ERS_Approved WHERE Approved_employee_ticket_id = " + employeeId + "", ERSMain.class).list();
		
		log.info("Search complete! Found: " + tickets.size() + " results");

		return tickets;
	}

	@Override
	public List<ERSMain> selectAll() {
		log.info("getting all Tickets from database....");
		Session ses = HibernateUtil.getSession();
		List<ERSMain> ERSMainList = ses.createQuery("from ERS_Approved", ERSMain.class).list();
		log.info("ERSMain list retrieved! Size: " + ERSMainList.size());
		return ERSMainList;
	}

	@Override
	public boolean update(ERSMain ticket) {
		log.info("Updating Ticket. Ticket info: " + ticket);

		ERSApproved Approved = new ERSApproved(ticket);
		
		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		ses.clear();

		ses.update(Approved);

		tx.commit();

		log.info("update complete");

		return true;
	}

	@Override
	public boolean delete(ERSMain ticket) {
		log.info("Deleting Ticket. Ticket info: " + ticket);

		ERSApproved Approved = new ERSApproved(ticket);
		
		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		ses.clear();

		ses.delete(Approved);

		tx.commit();

		log.info("deletion complete");

		return true;
	}
}
