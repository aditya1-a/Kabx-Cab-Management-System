package com.fproject.kabx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fproject.kabx.dao.AdminDao;
import com.fproject.kabx.dao.UserDao1;
import com.fproject.kabx.exception.CarException;
import com.fproject.kabx.exception.UserException;
import com.fproject.kabx.model.CarBrand;
import com.fproject.kabx.model.Cars;
import com.fproject.kabx.model.User;
import com.fproject.kabx.model.User.AccountHolderType;
import java.util.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	@Autowired
	private AdminDao adminDao;
	
	
//	@GetMapping("/userList")
//	public String getCustomers(Model model) throws UserException {
//		try {
//			
	
//			System.out.print("I am in user list");
//		User user = (User) session.getAttribute("user");
//		List<User> filtredUserList = adminDao.getListOfAllUser(user);
//		System.out.print("I am in userDDD");
//		//System.out.println(user);
//		System.out.print("I am in userFFF");
//		System.out.println(filtredUserList);
//		System.out.print("I am in userAAA");
//		//request.getSession().setAttribute("filteredUserList", user);
//		model.addAttribute("filteredUserList", user);
//			
//		List<User> customers = adminDao.getListOfAllCustomer(AccountHolderType.CUSTOMER);
//		System.out.println(customers);
//		System.out.println("Number of customers: " + customers.size());
//		model.addAttribute("customers", customers);		
//		return "userList";
//		}catch(UserException e) {
//			model.addAttribute("errorMessage", e.getMessage());
//			return "There is some internal error";
//			
//		}
//	}
	
	@GetMapping("/userList")
	public String searchCustomerBasedOnFirstName(@RequestParam(name = "firstName", required = false)String firstName,
			 @RequestParam(required = false) boolean reset, Model model) throws UserException{
		try {
				
			 List<User> allCustomers = adminDao.getListOfAllCustomer(AccountHolderType.CUSTOMER);
		     List<User> customers;
			
			if (reset) {
		        // Reset the search and fetch the entire list of customers
				System.out.println("I am in reset");
				customers = allCustomers;
	           
		    }
//			List<User> customers;
			else if (firstName != null && !firstName.isBlank()) {
		        // Search for customers by first name
		       customers = adminDao.searchCustomerByFirstName(firstName);
		       // model.addAttribute("customers", customers);
		    } 
		        else {
//		         Display the entire list of customers
		        customers = allCustomers;
		    }
		    model.addAttribute("customers", customers);
		    return "userList";
					
		}catch(UserException e) {
		model.addAttribute("errorMessage", e.getMessage());
		throw new UserException("Exception while finding the user: " +e.getMessage());
	}
	
	}
	
	@PostMapping("/deleteSelected")
	public String deleteCustomer(@ModelAttribute(name = "selectedIds")List<Integer> selectedIds,  
								Model model) throws UserException{
		try {
//			List<User> users = new ArrayList<>();
//			for(String id: selectedIds) {
//				User user = adminDao.deleteCustomerBasedOnID(Integer.parseInt(id));
//				users.add(user);
//			adminDao.deleteCustomerBasedOnID(users);
//			System.out.println(selectedIds);
			System.out.println("I m in delete Selected");
			System.out.println("Selected Ids: " +selectedIds);
			adminDao.deleteCustomerBasedOnID(selectedIds);
			model.addAttribute("successMessage", "Customer deleted Successfully");
			}catch(UserException e) {
				model.addAttribute("errorMessage", e.getMessage());
				throw new UserException("Exception while finding the user: " +e.getMessage());
			}
		System.out.println("I am above userList");						
		return "redirect:/userList";
			
		}
	
	@GetMapping("/driverList")
	public String searchDriver(@RequestParam(name = "firstName", required = false)String firstName,
								@RequestParam(required = false) boolean reset, Model model) throws UserException {
		try {
			
			List<User> allDriver = adminDao.getListofAllDriver(AccountHolderType.DRIVER);
			List<User> driver;
			
			if(reset) {
				driver = allDriver;
			} else if(firstName!=null && !firstName.isEmpty()) {
				driver = adminDao.searchDriverByFirstName(firstName);
			} else {
				driver = allDriver;
			}
		
		model.addAttribute("drivers",driver);
		return "driverList";
	}catch(UserException e) {
		model.addAttribute("errorMessage", e.getMessage());
		throw new UserException("Exception while finding the driver: " +e.getMessage());
	}
}
	
	@PostMapping("/deleteDriver")
	public String deleteDriver(@ModelAttribute(name = "selectedId") List<Integer> driverId,
			Model model) throws UserException{
		try {
			System.out.println("I am In delete Driver");
			System.out.println(driverId);
			adminDao.deleteDriverBasedOnID(driverId);
			model.addAttribute("sucessMessage", "Driver deleted Successfully");
			
		}catch(UserException e) {
			model.addAttribute("errormessage", e.getMessage());
			throw new UserException("Exception while finding the driver: "+e.getMessage());
		}
		return "redirect:/driverList";
		
	}
	
	@GetMapping("/approvalList")
	public String approvalList(Model model) throws UserException {
		List<User> driversList;
		try {
			driversList = adminDao.searchUserBasedOnAccountHolderTypeAndRequiresApproval(AccountHolderType.DRIVER, true);
			model.addAttribute("drivers", driversList);
			return "approvalList";
			}catch(UserException e) {
			model.addAttribute("errorMessage", e.getMessage());
			throw new UserException("Exception while finding the driver: " +e.getMessage());
			}
	}
	
	@PostMapping("/approvalList")
	public String approveDriver(@RequestParam(name = "selectedId") Long driverId,
			Model model, UserDao1 userDao) throws UserException{
		try {
			User user = adminDao.findBydriverID(driverId);
			System.out.println("I m above user");
			System.out.println(user);
			System.out.println("I m below user");
			if(user!=null && user.isRequiresApproval()==true && user.getAccountHolderType()==AccountHolderType.DRIVER) {
				System.out.println("I am inside method ");
				//user.setRequiresApproval(false);
				adminDao.updateRequiresApproval(driverId, false);
				model.addAttribute("sMessage", "Driver Approval Successful");
			} else {
				model.addAttribute("sMessage", "Inavlid Request");
			}
			
			System.out.println("I am above driver");
			List<User> driverList = adminDao.searchUserBasedOnAccountHolderTypeAndRequiresApproval(AccountHolderType.DRIVER, true);
			System.out.println(driverList);
			if(driverList==null) {
				model.addAttribute("sMessage", "No driver pending for approval");
			} else {
			model.addAttribute("drivers", driverList );
			}
			return "approvalList";
			
		}catch(UserException e) {
			model.addAttribute("errorMessage", e.getMessage());
			throw new UserException("Exception while approving the driver: " +e.getMessage());
			
		}
	}
	
	@GetMapping("/registerCarBrand")
	public String registrationPageCarBrand(@ModelAttribute("CarBrand") CarBrand carBrand){
		return "registerCarBrand";
		
	}
	
	@PostMapping("/registerCarBrand")
	public String registerCarBrand(@ModelAttribute("CarBrand") CarBrand carBrand, Model model) throws CarException{
		try {
			adminDao.registerCarBrand(carBrand);
			model.addAttribute("sMessage", "Car Brand Registered Successfully");
			System.out.println("sMessage added to Model object with value: " + model.getAttribute("sMessage"));
			return "registerCarBrand";
			
		}catch(CarException e) {
			model.addAttribute("errorMessage", e.getMessage());
			throw new CarException("exception while registering the Car Brand: " +e.getMessage());
			
		}
		
	}
	
	@GetMapping("/CarsList")
	public String registrationPageCarName(CarBrand carBrandName, @ModelAttribute("Cars") Cars cars, Model model, User user) throws UserException, CarException{
		try {
//		List<User> unassignedDriverList = adminDao.getUserByAccountHolderTypeAndCarIsNull(AccountHolderType.DRIVER, false);
//		model.addAttribute("unassignedDriver", unassignedDriverList);
//		Object value = unassignedDriverList.get(0);
//		System.out.println(value.getClass());
//		System.out.println("I am unassigned: "+unassignedDriverList);
			
		List<User> unassignedDriverList = adminDao.getUserByAccountHolderTypeAndCarIsNull(AccountHolderType.DRIVER, user.getUserid());
		model.addAttribute("unassignedDriver", unassignedDriverList);
		
		List<CarBrand> carBrandList = adminDao.getAllCarBrand(carBrandName.getCarBrandName());
		model.addAttribute("carBrands", carBrandList);
		Object AB = carBrandList.get(0);
		System.out.println(AB.getClass());	
		System.out.println("I am carBrand: "+carBrandList);
		
	//	model.addAttribute("car", new Cars()); //add an empty Car object to the model so that the JSP view can use it to bind the form data during submission.
		return "CarsList";
		
	}catch(CarException e) {
		model.addAttribute("errorMessage", e.getMessage());
		throw new CarException("exception while registering the Car Brand: " +e.getMessage());
		
	}
		
	}
	
	@PostMapping("/saveCarDetails")
	public String addCarDetails(@ModelAttribute("Cars") Cars cars, User user, Model model) throws CarException{
		try {
			System.out.println("I am in save");
			adminDao.registerCars(cars, user);
			model.addAttribute("sMessage", "Car Details saved successfully");
			System.out.println("sMessage added to Model object with value: " + model.getAttribute("sMessage"));
			return "CarsList";			
			
		}catch(CarException e) {
			model.addAttribute("errorMessage", e.getMessage());
			throw new CarException("exception while registering car details: " + e.getMessage());
		}
	}
		
	}
	
	


