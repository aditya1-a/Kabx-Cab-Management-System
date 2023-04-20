package com.fproject.kabx.model;

import org.springframework.stereotype.Component;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.*;


@Component
@Entity
@Table(name = "CarList")
public class Cars {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carID;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "carBrandId")
	private CarBrand carBrand;
	
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id", referencedColumnName = "userid")
	private User driver;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "userid")
	private User customer;
	
	@Column(nullable= false)
	private String carName;
	
	@Column(nullable= false)
	private String carNo;
	
	@Column(nullable=false)
	private String fromCity;
	
	@Column(nullable=false)
	private String toCity;
	
	@Column(nullable=false)
	private double distance;
	
	@Column(nullable=false)
	private double chargePerKm;
	
	@Column(nullable=false)
	private double minimumCharge;

	public Cars() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cars(int carID, CarBrand carBrand, User driver, 
			User customer, 
			String carName, String carNo, String fromCity,
			String toCity, double distance, double chargePerKm, double minimumCharge) {
		super();
		this.carID = carID;
		this.carBrand = carBrand;
		this.driver = driver;
		this.customer = customer;
		this.carName = carName;
		this.carNo = carNo;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.distance = distance;
		this.chargePerKm = chargePerKm;
		this.minimumCharge = minimumCharge;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public CarBrand getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(CarBrand carBrand) {
		this.carBrand = carBrand;
	}

	public User getDriver() {
		return driver;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getChargePerKm() {
		return chargePerKm;
	}

	public void setChargePerKm(double chargePerKm) {
		this.chargePerKm = chargePerKm;
	}

	public double getMinimumCharge() {
		return minimumCharge;
	}

	public void setMinimumCharge(double minimumCharge) {
		this.minimumCharge = minimumCharge;
	}

	

}
