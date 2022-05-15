package com.revature.dao;

import java.util.List;

import com.revature.models.Manager;

public interface ManagerDAO {

	public int insert(Manager user);

	public Manager selectById(int id);

	public Manager selectByFirstName(String firstName);

	public List<Manager> selectAll();

	public boolean update(Manager user);

	public boolean delete(Manager user);

}