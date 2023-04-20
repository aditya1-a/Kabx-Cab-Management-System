package com.fproject.kabx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fproject.kabx.dao.UserDao1;
import com.fproject.kabx.exception.UserAuthException;
import com.fproject.kabx.exception.UserException;
import com.fproject.kabx.model.Address;
import com.fproject.kabx.model.User;
import com.fproject.kabx.model.User.AccountHolderType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterController {
	
	@Autowired
	private UserDao1 userDao;
	
	
	@GetMapping("/CabManagement")
	public String home(UserDao1 userDao, HttpServletRequest request) {
		return "CabManagement";
	}
	
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
	    return "register";
	
	}

	
	@PostMapping("/saveregister")
	public String registerUser(@ModelAttribute("user") User user, Model model, HttpSession session, UserDao1 userDao, HttpServletRequest request) {
		try {
		
		AccountHolderType accHolderType = user.getAccountHolderType();
		if(accHolderType == AccountHolderType.DRIVER) {
			user.setRequiresApproval(true);
			userDao.registerUser(user);
			model.addAttribute("message", "Registration successful. Your request for approval is pending");
		} else if(accHolderType == AccountHolderType.ADMIN || accHolderType == AccountHolderType.CUSTOMER ) {
			userDao.registerUser(user);
			model.addAttribute("sMessage", "Registration Successful");
		} else {
			model.addAttribute("message", "Invalid Account holder type");
			return "register";
			
		}
		return "register";
		
			
//		System.out.println(user);
//		System.out.println("I m here");
//			userDao.registerUser(user);
//			System.out.println("I m this");
//			return "CabManagement" ;//"redirect:/login";
		} catch (UserException e) {
			// TODO Auto-generated catch block
			model.addAttribute("errorMessage", e.getMessage());
			System.out.println("I m in exception");
			return "There is some internal error";
		}
		
		
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model, UserDao1 userDao, HttpServletRequest request) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@PostMapping("/home")
	public String login(@ModelAttribute("user") User user, HttpSession session, UserDao1 userDao, Model model) throws UserAuthException, UserException {
		String email = user.getEmail();
		String password = user.getPassword();
		User authUser = userDao.login(email, password);
		if(authUser!=null) {
			AccountHolderType accHolderType = authUser.getAccountHolderType();
			if(accHolderType == AccountHolderType.DRIVER) {
				if(authUser.isRequiresApproval()) {
					model.addAttribute("error", "Your account requires Admin Approval");
					return "login";
				} else {
					session.setAttribute("currentUser", authUser);
					model.addAttribute("message", "Welcome, " +authUser.getFirstName() + "!");
					return "Driverhome";
				}
			} else if (accHolderType == AccountHolderType.CUSTOMER) {
				session.setAttribute("currentUser", authUser);
				model.addAttribute("message", "Welcome,  " +authUser.getFirstName() + "!");
				return "CustomerHome";
			} else if(accHolderType == AccountHolderType.ADMIN) {
				session.setAttribute("currentUser", authUser);
				model.addAttribute("message", "Welcome, " +authUser.getFirstName() + "!");
				return "home";
			}
		}
		model.addAttribute("error", "Invalid email or password");
		return "login";
	}
		
//		User authUser = userDao.login(user.getEmail(), user.getPassword());
//		System.out.println(user.getEmail());
//		System.out.println(user.getPassword());
		
		
//		if(authUser!=null) {
//			session.setAttribute("currentUser", authUser);
//			return "home";
//		} else {
//			return "login";
//		}
//	}
	
	@GetMapping("/home")
	public String homepage( Model model, HttpSession session ) {
		User authUser = (User) session.getAttribute("currentUser");
		System.out.println("I am in homeget");
		if(authUser == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("user", authUser);
		return "home";
		}
	}
	
	@GetMapping("/user/logout")
	public String logout(HttpSession session, UserDao1 userDao, HttpServletRequest request) {
		session.invalidate();
		return "redirect:/login";
	}
}
