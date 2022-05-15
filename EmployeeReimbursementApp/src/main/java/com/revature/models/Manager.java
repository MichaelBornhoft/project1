package com.revature.models;
import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="store_Managers")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Manager {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_Manager_id")
	private int id;
	
	@Column(name="store_Manager_Managername")
	private String Managername;
	
	@Column(name="store_Manager_password")
	private String password;
	
	@Column(name="store_Manager_firstname")
	private String firstName;
	
	@Column(name="store_Manager_lastname")
	private String lastName;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(String Managername, String password, String firstName, String lastName) {
		super();
		this.Managername = Managername;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Manager(int id, String Managername, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.Managername = Managername;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
