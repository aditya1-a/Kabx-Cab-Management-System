//package com.fproject.kabx.dao.Impl;
//import com.fproject.kabx.dao.*;
//import com.fproject.kabx.model.User;
//import com.fproject.kabx.dao.Dao;
//import com.fproject.kabx.exception.UserAuthException;
//import com.fproject.kabx.exception.UserException;
//
//
//
//
//import java.util.logging.Logger;
//
//import org.hibernate.HibernateException;
//import org.hibernate.query.Query;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//@Component
//public class UserDaoImpl extends Dao implements UserDao {
//	
//	private  static final Logger log  = Logger.getAnonymousLogger();
//	
//	public  UserDaoImpl() {
//		
//	}
//
//	@Override
//	public User registerUser(User user) throws UserException {
//		
//		try {
//		begin();
//		getSession().persist(user);;
//		commit();
//		log.info("User registered successfully  " +user.getFirstName());
//		return user;
//		} catch(HibernateException e) {
//			rollback();
//			throw new UserException("Exception while creating user:  "  + e.getMessage());
//		}
//		
//	}
//	
//	
//	
//	@Override
//	public User findUserByEmail(String email) throws UserAuthException {
//		// TODO Auto-generated method stub
//	try {
//		begin();
//		Query<User> query = getSession().createQuery("FROM User where email = :email", User.class);		
//		query.setParameter("email", email);
//		User user = query.uniqueResult();
//		commit();		
//		return user;
//		} catch(HibernateException e) {
//			rollback();
//			throw new UserAuthException("Exception while finding user:  "  + e.getMessage());		
//		}	
//	}
//
//	@Override
//	public User login(String email, String password) throws UserAuthException, UserException {
//		User user = findUserByEmail(email);	
//		if(!user.getPassword().equals(password)) {
//				return user;
//		} else {
//			throw new UserAuthException("Inavlid Email or Password");
//		}	
//	
//
//	}
//	
//	@Override
//	public void logout(User user) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	
//	
//
//
//
//}
