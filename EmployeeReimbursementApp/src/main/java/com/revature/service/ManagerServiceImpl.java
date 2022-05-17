package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.ManagerDAO;
import com.revature.dao.ManagerDAOImpl;
import com.revature.models.Employee;
import com.revature.models.Manager;

public class ManagerServiceImpl implements ManagerService{
	
	private ManagerDAO mdao;
	private EmployeeDAO edao; 
	private static Logger log = Logger.getLogger(ManagerServiceImpl.class);
	
	public ManagerServiceImpl() {
		super();
	}

	//introducing dependency injection through use of a construction injection
	//so we are not manually injecting what we need for this class; we are handing that control over to the application
	public ManagerServiceImpl(ManagerDAOImpl dao) {
		super();
		this.mdao = dao; //this is the same as this: private static ManagerDAO udao = new ManagerDAOImpl();
	}

	@Override
	public Manager login(String Managername, String password) {
		log.info("in service layer. Logging in Manager with creds: " + Managername + ", " + password);
		//utilize Streams API
		Optional<Manager> Managers = mdao.selectAll()
				.stream()
				.filter(u -> (u.getManagername().equals(Managername) && u.getPassword().equals(password))) //filter out to all Managers that match criteria/condition
				.findFirst(); //returns the element that is left after filtering
		return (Managers.isPresent() ? Managers.get() : null);
	}

	@Override
	public int register(Manager Manager) {
		log.info("in service layer. Registering Manager: " + Manager);
		return mdao.insert(Manager);
	}

	@Override
	public Manager findManagerById(int id) {
		log.info("in service layer. searching Manager by id: " + id);
		return mdao.selectById(id);
	}


	@Override
	public boolean editManager(Manager Manager) {
		log.info("in service layer. editing Manager: " + Manager);
		return mdao.update(Manager);
	}

	@Override
	public boolean deleteManager(Manager Manager) {
		log.info("in service layer. removing Manager: " + Manager);
		return mdao.delete(Manager);
	}

	@Override
	public List<Employee> findAllEmployees() {
		log.info("in service layer. finding all Employees...");
		return edao.selectAll();
}

	
}
