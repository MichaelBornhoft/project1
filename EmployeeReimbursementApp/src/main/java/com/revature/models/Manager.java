package com.revature.models;
import java.util.Objects;

import javax.persistence.*;



@Entity
@Table(name="Managers")

public class Manager {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Manager_id")
	private int id;
	
	@Column(name="Manager_Managername")
	private String Managername;
	
	@Column(name="Manager_password")
	private String password;
	
	@Column(name="Manager_firstname")
	private String firstName;
	
	@Column(name="Manager_lastname")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManagername() {
		return Managername;
	}

	public void setManagername(String managername) {
		Managername = managername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Managername, firstName, id, lastName, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		return Objects.equals(Managername, other.Managername) && Objects.equals(firstName, other.firstName)
				&& id == other.id && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", Managername=" + Managername + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}
	
	
}
