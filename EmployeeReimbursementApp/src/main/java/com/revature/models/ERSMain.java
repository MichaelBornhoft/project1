package com.revature.models;

import java.util.Objects;

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
	private Manager mandao; 

	@Id // specifies this field as a primary key
	@Column(name = "ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // this is equivalent to the SERIAL keyword in SQL
	private int ticketId;

	// this is to map employee id to a request.
	@Column(name = "employee_ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeTicketId;
	
	@Column(name = "manager_ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int managerTicketId;

	@Column(name = "ticket_name", unique = true, nullable = false) // similar to adding UNIQUE NOT NULL constraint in //
																	// SQL
	private String ticketName;

	@Column(name = "ticket_description", unique = true, nullable = false)
	private String description;

	// amount of request
	@Column(name = "ticket_amount", precision = 10, scale = 2)
	private double ticketAmount;

	// status of request. 0 = denied, 1 = pending, 2 = approved
	// how does pending table connect to the number 1. 
	@Column(name = "ticket_status", unique = true, nullable = false)
	private String ticketStatus; 

	public ERSMain() {
		super();
	}

	public ERSMain(String ticketName, String description, double ticketAmount, String ticketStatus) {
		super();
		this.ticketName = ticketName;
		this.description = description;
		this.ticketAmount = ticketAmount;
		this.ticketStatus = ticketStatus;
	}

	public ERSMain(int ticketId, int employeeTicketId, int managerTicketId, String ticketName, String description, double ticketAmount,
			String ticketStatus) {
		super();
		this.ticketId = ticketId;
		this.ticketName = ticketName;
		this.description = description;
		this.ticketAmount = ticketAmount;
		this.ticketStatus = ticketStatus;
	}
		
	
	
	
	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(double ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, ticketAmount, ticketId, ticketName, ticketStatus);
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
		return Objects.equals(description, other.description)
				&& Double.doubleToLongBits(ticketAmount) == Double.doubleToLongBits(other.ticketAmount)
				&& ticketId == other.ticketId && Objects.equals(ticketName, other.ticketName)
				&& ticketStatus == other.ticketStatus;
	}

	@Override
	public String toString() {
		return "ERSMain [ticketId=" + ticketId + ", ticketName=" + ticketName + ", description=" + description
				+ ", ticketAmount=" + ticketAmount + ", ticketStatus=" + ticketStatus + "]";
	}
	public int getemployeeTicketId(int employeeTicketId) {
		return emdao.getEmployeeId();
		
	}
	
	public int getmanagerTicketId(int managerTicketId) {
		return mandao.getManagerId(); 
	}
	



}
