package com.revature.models;

import java.util.Objects;

import com.revature.models.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//JPA is the Java Persistence API
//This API allows us access to annotations that can map our fields to DB columns and constraints

@Entity
@Table(name = "ERS_Ticket") // now the table will show up as 'tickets' instead of 'ticket' (the name of the
							// class) in the database
public class ERSMain {
	private Employee emdao;

	@Id // specifies this field as a primary key
	@Column(name = "ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // this is equivalent to the SERIAL keyword in SQL
	private int ticketId;

	
	//this is to map employee id to a request. 
	@Column(name = "employee_ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeTicketId() {

		return emdao.getEmployeeId();
	}

	@Column(name = "ticket_name", unique = true, nullable = false) // similar to adding UNIQUE NOT NULL constraint in																// SQL
	private String ticketName;

	@Column(name = "ticket_description", unique = true, nullable = false)
	private String description;

	// amount of request
	private double ticketAmount;

	// status of request. 0 = denied, 1 = pending, 2 = approved
	private int ticketStatus = 1;

	public ERSMain() {
		super();
	}

	public ERSMain(String ticketName, String description) {
		super();
		this.ticketName = ticketName;
		this.description = description;
	}

	public ERSMain(int ticketId, String ticketName, String description) {
		super();
		this.ticketId = ticketId;
		this.ticketName = ticketName;
		this.description = description;
	}

	public int getticketId() {
		return ticketId;
	}

	public void setticketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getticketName() {
		return ticketName;
	}

	public void setticketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ticketId, ticketName, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ERSMain other = (ERSMain) obj;
		return ticketId == other.ticketId && Objects.equals(ticketName, other.ticketName)
				&& Objects.equals(description, other.description);
	}

	@Override
	public String toString() {
		return "ticket [ticketId=" + ticketId + ", ticketName=" + ticketName + ", description=" + description + "]";
	}
}
