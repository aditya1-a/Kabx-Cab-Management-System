package com.fproject.kabx.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

import java.util.*;

@Component
@Entity
@Table(name = "CarBrandList")
public class CarBrand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carBrandId;
	
	@Column(nullable = false, unique = true)
	private String carBrandName;
	
	@OneToMany(mappedBy = "carBrand",  cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cars> cars = new ArrayList<>();

	public CarBrand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarBrand(int carBrandId, String carBrandName, List<Cars> cars) {
		super();
		this.carBrandId = carBrandId;
		this.carBrandName = carBrandName;
		this.cars = cars;
	}
	
	public CarBrand(int carBrandId, String carBrandName) {
		super();	
		this.carBrandId = carBrandId;
		this.carBrandName = carBrandName;
	}
	
	public CarBrand(String carBrandName) {
		super();	
		this.carBrandName = carBrandName;
	}


	
	

	public int getCarBrandId() {
		return carBrandId;
	}

	public void setCarBrandId(int carBrandId) {
		this.carBrandId = carBrandId;
	}

	public String getCarBrandName() {
		return carBrandName;
	}

	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}

	public List<Cars> getCars() {
		return cars;
	}

	public void setCars(List<Cars> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "CarBrand [carBrandId=" + carBrandId + ", carBrandName=" + carBrandName + "]";
	}
	
	

}
