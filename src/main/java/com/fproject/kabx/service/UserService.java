//package com.fproject.kabx.service;
//
//import org.springframework.http.ResponseEntity;
//
//import com.fproject.kabx.dao.UserDao;
//import com.fproject.kabx.exception.UserAuthException;
//import com.fproject.kabx.exception.UserException;
//import com.fproject.kabx.model.User;
//
//public class UserService {
//	
//	private UserDao userDao;
//	
//	public UserService(UserDao userDao) {
//		this.userDao = userDao;
//	}
//	
//	public void registerUser(User user) {
//		try {
//			userDao.registerUser(user);
//		} catch (UserException e) {
//			e.printStackTrace();
//		}
//	}
	
//	public User login(String email, String password) throws UserAuthException, UserException {
//		User user = userDao.findUserByEmail(email);	
//		if(!user.getPassword().equals(password)) {
//				return user;
//		} else {
//			throw new UserAuthException("Inavlid Email or Password");
//		}	
//	
//	}
	
//	public void logout(User user) {
//		if(user!=null) {
//			userDao.logout(user);
//		}
//	}
	

//}
