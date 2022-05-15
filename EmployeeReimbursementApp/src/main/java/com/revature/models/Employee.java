package com.revature.models;
import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="store_Employees")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_Employee_id")
	private int id;
	
	@Column(name="store_Employee_Employeename")
	private String Employeename;
	
	@Column(name="store_Employee_password")
	private String password;
	
	@Column(name="store_Employee_firstname")
	private String firstName;
	
	@Column(name="store_Employee_lastname")
	private String lastName;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String Employeename, String password, String firstName, String lastName) {
		super();
		this.Employeename = Employeename;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Employee(int id, String Employeename, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.Employeename = Employeename;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}