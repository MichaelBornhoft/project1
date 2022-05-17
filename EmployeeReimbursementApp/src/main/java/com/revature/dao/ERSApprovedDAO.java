package com.revature.dao;

import java.util.List;

import com.revature.models.ERSMain;



public interface ERSApprovedDAO {
	
	public int insert(ERSMain ticket);

	public ERSMain selectById(int ticketId);

	public ERSMain selectByemployeeTicketId(int employeeId);

	public List<ERSMain> selectAll();

	public boolean update(ERSMain ticket);

	public boolean delete(ERSMain ticket);

}
