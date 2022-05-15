package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO{
	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);
	
	@Override
	public int insert(Employee Employee) {
		log.info("adding Employee to database. Employee info: " + Employee);
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
		
		int pk = (int) ses.save(Employee);
		
		tx.commit();
		
		log.info("insert successful! New Employee id is " + pk);
		return pk; //return the auto-generated pk
	}

	@Override
	public Employee selectById(int id) {
		log.info("searching Employee by id: " + id);
		
		Session ses = HibernateUtil.getSession();
		
		Employee Employee = (Employee) ses.createNativeQuery("SELECT * FROM store_Employees WHERE store_Employee_id = " + id + "", Employee.class).getSingleResult();
		
		log.info("Search complete! Found: " + Employee);
		
		return Employee;
	}

	@Override
	public Employee selectByFirstName(String firstName) {
		log.info("searching Employee by firstName: " + firstName);
		
		Session ses = HibernateUtil.getSession();
		
		Employee Employee = (Employee) ses.createNativeQuery("SELECT * FROM store_Employees WHERE store_Employee_firstname = '" + firstName + "'", Employee.class).getSingleResult();
		
		log.info("Search complete! Found: " + Employee);
		
		return Employee;
	}

	@Override
	public List<Employee> selectAll() {
		log.info("getting all Employees from database....");
		Session ses = HibernateUtil.getSession();
		List<Employee> EmployeeList = ses.createQuery("from Employee", Employee.class).list();
		log.info("Employee list retrieved! Size: " + EmployeeList.size());
		return EmployeeList;
	}

	@Override
	public boolean update(Employee Employee) {
		log.info("Updating Employee. Employee info: " + Employee);
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
		
		ses.clear();
		
		ses.update(Employee);
		
		tx.commit();
		
		log.info("update complete");
		
		return true;
	}

	@Override
	public boolean delete(Employee Employee) {
		log.info("Deleting Employee. Employee info: " + Employee);
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
		
		ses.clear();
		
		ses.delete(Employee);
		
		tx.commit();
		
		log.info("deletion complete");
		
		return true;
	}

}
