//package com.fproject.kabx.model;
//
//import org.springframework.stereotype.Component;
//
//import jakarta.persistence.*;
//
//@Component
//@Entity
//public class Driver {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private String driverID;
//	
//	@OneToOne(cascade = CascadeType.ALL)//Any operation performed on driver entity should be cascaded to address entity as well
//	@JoinColumn(name  = "Address_ID", referencedColumnName = "addressID")
//	private Address address;
//	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "User_ID", referencedColumnName = "userid")//name of column in Driver Table and it referenced to userid table
//	private User user;
//
//	public Driver() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Driver(String driverID, Address address, User user) {
//		super();
//		this.driverID = driverID;
//		this.address = address;
//		this.user = user;
//	}
//
//	public String getDriverID() {
//		return driverID;
//	}
//
//	public void setDriverID(String driverID) {
//		this.driverID = driverID;
//	}
//
//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//	
//}
