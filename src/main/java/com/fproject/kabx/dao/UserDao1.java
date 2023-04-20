package com.fproject.kabx.dao;

import com.fproject.kabx.dao.*;
import com.fproject.kabx.model.Address;
import com.fproject.kabx.model.User;
import com.fproject.kabx.dao.Dao;
import com.fproject.kabx.exception.UserAuthException;
import com.fproject.kabx.exception.UserException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;





import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class UserDao1 extends Dao{
	private  static final Logger log  = Logger.getAnonymousLogger();
	


	public void registerUser(User user) throws UserException {
		
		try {
		begin();
		System.out.println("I m in ARregister user");
		System.out.println(user);
		getSession().persist(user);
		System.out.println("I m in register user");
		commit();
		log.info("User registered successfully  " +user.getFirstName());
//		return user;
		} catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user:  "  + e.getMessage());
		}
		
	}
	
		
	
	public User findUserByEmail(String email) throws UserAuthException {
		// TODO Auto-generated method stub
	try {
		begin();
//		Query<User> query = getSession().createQuery("FROM User where email = :email", User.class);
//		Query<User> query = getSession().createQuery("FROM User u WHERE u.email = :email", User.class);
//		query.setParameter("email", email);
//		System.out.print("I m in findmyemail");
//		System.out.print(email);
//		User user = query.uniqueResult();
//		System.out.print(user);
//		System.out.print("I m i FINDMYEMAIL AFTER USER");
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder(); //obtains a CriteriaBuilder instance from the current Hibernate Session. The CriteriaBuilder is used to create criteria queries and expressions.
		CriteriaQuery<User> query = builder.createQuery(User.class); //creates a CriteriaQuery object for the User entity, which specifies the type of the query result.
		Root<User> root = query.from(User.class); //This line creates a Root object for the User entity, which represents the root of the query. The Root object is used to specify the entity to be queried and its attributes.
		query.select(root).where(builder.equal(root.get("email"), email)); //specifies the query to select a User entity where the email attribute equals the specified email

		User user = getSession().createQuery(query).uniqueResult();

		commit();		
		return user;
		} catch(HibernateException e) {
			rollback();
			throw new UserAuthException("Exception while finding user:  "  + e.getMessage());		
		}	
	}


	public User login(String email, String password) throws UserAuthException, UserException {
		User user = findUserByEmail(email);	
		if (user == null) {
	        System.out.println("EMailnot found");
	    }
		if(user.getPassword().equals(password)) {
				return user;
		} else {
			throw new UserAuthException("Inavlid Email or Password");
		}	
	

	}
	

	public void logout(User user) {
		// TODO Auto-generated method stub
		
	}

}
