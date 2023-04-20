package com.fproject.kabx.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "UserDetails")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	@Column(nullable = false)
	private String firstName;
	private String lastName;
	
	@Column(nullable = false, unique= true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	private long phoneNo;
	private String gender;
	
	public enum AccountHolderType {
        ADMIN,
        DRIVER,
        CUSTOMER
    }
	
    @Enumerated(EnumType.STRING)
    @Column(name = "account_holder_type")
    private AccountHolderType accountHolderType;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "addressID")
	private Address address;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	private List<Cars> customerCars = new ArrayList<>(); 
	
	@OneToOne(mappedBy = "driver")
	private Cars car;
	
	@Column(name = "Approval Required")
	private boolean requiresApproval;
	
//	private boolean assignedToCar;
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public User(int userid, String firstName, String lastName, String email, String password, long phoneNo,
			String gender, AccountHolderType accountHolderType, Address address, Cars car,
			List<Cars> customerCars, boolean requiresApproval 
			//boolean assignedToCar
			) {
		super();
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.accountHolderType = accountHolderType;
		this.address = address;
		this.car = car;
		this.customerCars = customerCars;
		this.requiresApproval = requiresApproval;
//		this.assignedToCar =assignedToCar;
	}

	

	public User(int userid, String firstName, String lastName, String email, long phoneNo, String gender,
			AccountHolderType accountHolderType,boolean requiresApproval ) {
		super();
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.accountHolderType = accountHolderType;
		this.requiresApproval = requiresApproval;
	}


	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public AccountHolderType getAccountHolderType() {
		return accountHolderType;
	}


	public void setAccountHolderType(AccountHolderType accountHolderType) {
		this.accountHolderType = accountHolderType;
	}

	
	

	public Cars getCar() {
		return car;
	}


	public void setCar(Cars car) {
		this.car = car;
	}


//	public boolean isAssignedToCar() {
//		return assignedToCar;
//	}
//
//
//	public void setAssignedToCar(boolean assignedToCar) {
//		this.assignedToCar = assignedToCar;
//	}


	public List<Cars> getCustomerCars() {
		return customerCars;
	}


	public void setCustomerCars(List<Cars> customerCars) {
		this.customerCars = customerCars;
	}

	
	
	public boolean isRequiresApproval() {
		return requiresApproval;
	}


	public void setRequiresApproval(boolean requiresApproval) {
		this.requiresApproval = requiresApproval;
	}


	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", phoneNo=" + phoneNo + ", gender=" + gender + ", accountHolderType="
				+ accountHolderType + ", address=" + address + ", customerCars=" + customerCars + ", car=" + car
				+ ", requiresApproval=" + requiresApproval + "]";
	}


	
	

}
