package com.fproject.kabx.model;
import java.util.List;
import java.util.*;
import org.springframework.stereotype.Component;
import jakarta.persistence.*;

@Component
@Entity
@Table(name = "AddressDetails")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressID;
	
	@Column(length = 50)
	private String apartment;
	
	@Column(length = 50)
	private String street;
	
	private String city;
	private String country;
	private String pin;
	
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	private List<User> users = new ArrayList<>(); 
	

	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(int addressID, String apartment, String street, String city, String country, String pin,
			List<User> users) {
		super();
		this.addressID = addressID;
		this.apartment = apartment;
		this.street = street;
		this.city = city;
		this.country = country;
		this.pin = pin;
		this.users = users;
	}

	public int getAddressID() {
		return addressID;
	}


	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "Address [addressID=" + addressID + ", apartment=" + apartment + ", street=" + street + ", city=" + city
				+ ", country=" + country + ", pin=" + pin + ", users=" + users + "]";
	}

	
	
	

}
