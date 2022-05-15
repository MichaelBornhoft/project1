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
@Table(name="Employees") //now the table will show up as 'Employees' instead of 'Employee' (the name of the class) in the database
public class Employee {
	
	@Id //specifies this field as a primary key
	@Column(name="Employee_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //this is equivalent to the SERIAL keyword in SQL
	private int EmployeeId;
	
	@Column(name="Employee_name", unique=true, nullable=false) //similar to adding UNIQUE NOT NULL constraint in SQL
	private String EmployeeName;
	
	//this will automatically be created as a column called "description" in the database
	private String description;

	public Employee() {
		super();
	}

	public Employee(String EmployeeName, String description) {
		super();
		this.EmployeeName = EmployeeName;
		this.description = description;
	}

	public Employee(int EmployeeId, String EmployeeName, String description) {
		super();
		this.EmployeeId = EmployeeId;
		this.EmployeeName = EmployeeName;
		this.description = description;
	}

	public int getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(int EmployeeId) {
		this.EmployeeId = EmployeeId;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String EmployeeName) {
		this.EmployeeName = EmployeeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(EmployeeId, EmployeeName, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return EmployeeId == other.EmployeeId && Objects.equals(EmployeeName, other.EmployeeName)
				&& Objects.equals(description, other.description);
	}

	@Override
	public String toString() {
		return "Employee [EmployeeId=" + EmployeeId + ", EmployeeName=" + EmployeeName + ", description=" + description + "]";
	}
} // this is equivalent to the SERIAL keyword in SQL