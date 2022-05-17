package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class ERSPending {

	@Entity
	@Table(name = "ERS_Pending") // now the table will show up as 'tickets' instead of 'ticket' (the name of the
									// class) in the database
	public class ERSApproved {

		private Employee emdao;
		private ERSMain erm;

		@Id // specifies this field as a primary key
		@Column(name = "pending_ticket_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY) // this is equivalent to the SERIAL keyword in SQL
		private int ticketId() {
			return erm.getTicketId();
		}

		// this is to map employee id to a request.
		@Column(name = "pending_employee_ticket_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int employeeTicketId() {

			return emdao.getEmployeeId();
		}

		@Column(name = "pending_ticket_name", unique = true, nullable = false) // similar to adding UNIQUE NOT NULL constraint
																		// in // SQL
		private String ticketName() {
			return erm.getTicketName();
		}

		@Column(name = "pending_ticket_description", unique = true, nullable = false)
		private String description() {
			return erm.getDescription();
		}

		// amount of request
		@Column(name = "pending_ticket_amount", precision = 10, scale = 2)
		private double ticketAmount() {
			return erm.getTicketAmount();
		}

		// status of request. 0 = denied, 1 = pending, 2 = approved
		@Column(name = "pending_ticket_status")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int ticketStatus() {
			return erm.getTicketStatus();
		}

	}

}
