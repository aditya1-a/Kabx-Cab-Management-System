package com.fproject.kabx.dao;

import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.fproject.kabx.exception.CarException;
import com.fproject.kabx.exception.UserException;
import com.fproject.kabx.model.CarBrand;
import com.fproject.kabx.model.Cars;
import com.fproject.kabx.model.User;
import com.fproject.kabx.model.User.AccountHolderType;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import java.util.*;



@Component
public class AdminDao extends Dao {

	private  static final Logger log  = Logger.getAnonymousLogger();
	
	public List<User> getListOfAllCustomer(AccountHolderType Customer) throws UserException {
		try {
		begin();		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        // Define the columns to be selected
        query.select(builder.construct(User.class, 
        	root.get("userid"),	
            root.get("firstName"),
            root.get("lastName"),
            root.get("email"),
            root.get("phoneNo"),
            root.get("gender"),
            root.get("accountHolderType"),
            root.get("requiresApproval")
        ));

        // Add the condition to filter by accountHolderType
        query.where(builder.equal(root.get("accountHolderType"), AccountHolderType.CUSTOMER));

        List<User> userList = getSession().createQuery(query).getResultList();

        commit();	
		return userList;
		}catch(HibernateException e ) {
			rollback();		
			throw new UserException("Exception while finding user:  "  + e.getMessage());
		}
	}
	
	public List<User> searchCustomerByFirstName(String firstName) throws UserException{
		try {
			begin();
			CriteriaBuilder builder  = getSession(). getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			
			//Define column to be selected
			query.select(builder.construct(User.class, 
					root.get("userid"),
					root.get("firstName"),
					root.get("lastName"),
					root.get("email"),
					root.get("phoneNo"),
					root.get("gender"),
					root.get("accountHolderType"),
					root.get("requiresApproval")
					));
			
			//Add the condition to filter by accountHolderType and firstName
			query.where(
					builder.and(
							builder.equal(root.get("accountHolderType"), AccountHolderType.CUSTOMER),
							builder.like(root.get("firstName"), "%" + firstName + "%")
							)
					);
			
			List<User> userList = getSession().createQuery(query).getResultList();
			System.out.println("I m in AdminDao");
			System.out.println(userList);
			System.out.println("I m in AdminDao");
			return userList;			
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while finding the user: " +e.getMessage());
		}
	
	}
	
	public void deleteCustomerBasedOnID(List<Integer> userId) throws UserException{
		try {
			begin();
//			CriteriaBuilder builder = getSession().getCriteriaBuilder();
//			CriteriaDelete<User> deleteQuery = builder.createCriteriaDelete(User.class);
//			Root<User> root = deleteQuery.from(User.class);
//			
//			//Add the condition to filter by userID and accountholderType
//			deleteQuery.where(builder.and(
//					builder.equal(root.get("userid"), userId),
//					builder.equal(root.get("accountHolderType"), AccountHolderType.CUSTOMER)
//					));
//			
//			int deleteCount = getSession().createQuery(deleteQuery).executeUpdate();
//			
//			if(deleteCount==0) {
//				throw new UserException("User not found or not customer");
//			}
			System.out.println("I am in Admin Dao");
			
			Query query = getSession().createQuery("delete from User where userid in (:userIds)");
			query.setParameterList("userIds", userId);
	        query.executeUpdate();
	        commit();
			
			
		} catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while finding the user: " +e.getMessage());
		}
	}
	
	public List<User> getListofAllDriver(AccountHolderType driver) throws UserException{
		try {
			begin();
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
		
			System.out.println("I m in getList of all driver");
			//Define the columns to be selected
			query.select(builder.construct(User.class,
					root.get("userid"),
					root.get("firstName"),
					root.get("lastName"),
					root.get("email"),
					root.get("phoneNo"),
					root.get("gender"),
					root.get("accountHolderType"),
					root.get("requiresApproval")
					));
			
			//Add the condition to filter
			query.where(builder.equal(root.get("accountHolderType"), AccountHolderType.DRIVER));
			
			List<User> driverList = getSession().createQuery(query).getResultList();
			System.out.println("In driverList: "+driverList);
			return driverList;
					
		} catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while finding the user: " +e.getMessage());
		}
		
	}
	
	public List<User> searchDriverByFirstName(String firstName) throws UserException{
		try {
			begin();
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			
			//Define the column to be selected
			query.select(builder.construct(User.class,
					root.get("userid"),
					root.get("firstName"),
					root.get("lastName"),
					root.get("email"),
					root.get("phoneNo"),
					root.get("gender"),
					root.get("accountHolderType"),
					root.get("requiresApproval")
					));
			
			query.where(builder.and(
									builder.equal(root.get("accountHolderType"), AccountHolderType.DRIVER),
									builder.like(root.get("firstName"), "%" + firstName + "%" )
									)
					);
			List<User> driverList = getSession().createQuery(query).getResultList();
			return driverList;
			
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Error while finding driver: " +e.getMessage());
		}
	}
	
	public void deleteDriverBasedOnID(List<Integer> driverId) throws UserException{
		try {
			begin();
			System.out.println("I am In adminDao Driver");
			Query query = getSession().createQuery("delete from User where userid in (:driverIds) ");
			query.setParameterList("driverIds", driverId);
			query.executeUpdate();
			System.out.println("I am here");
			commit();
			
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Error while finding driver: " + e.getMessage());
		}
	}
	
	public List<User> searchUserBasedOnAccountHolderTypeAndRequiresApproval(AccountHolderType driver ,
			boolean approval)throws UserException {
		
		try {
			begin();
			System.out.println("I m hereee");
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			System.out.println("I m hereee");
			
			//Define the column to be selected
			query.select(builder.construct(User.class,
					root.get("userid"),
					root.get("firstName"),
					root.get("lastName"),
					root.get("email"),
					root.get("phoneNo"),
					root.get("gender"),
					root.get("accountHolderType"),
					root.get("requiresApproval")
					));
			
//			if(driver!=null) {
				query.where(builder.and(builder.equal(root.get("accountHolderType"), driver),
						builder.equal(root.get("requiresApproval"), true)));
//			} else {
//				query.where(builder.and(builder.equal(root.get("accountHolderType"), driver),
//						builder.equal(root.get("requiresApproval"), approval)));
//			}
			
			List<User> driverList = getSession().createQuery(query).getResultList();
			System.out.println("I am in search method: "+driverList);
			if (driverList.isEmpty()) {
                //throw new UserException("No drivers found with the specified parameters");
				System.out.println("No driver pending");
            }
			commit();
			return driverList;
			
			
			
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Error while finding driver: " + e.getMessage());
		}
	
	}

	public User findBydriverID(Long driverId) throws UserException {
		try {
			begin();
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			
			//Define the column to be selected
			query.select(builder.construct(User.class,
					root.get("userid"),
					root.get("firstName"),
					root.get("lastName"),
					root.get("email"),
					root.get("phoneNo"),
					root.get("gender"),
					root.get("accountHolderType"),
					root.get("requiresApproval")
					));
			
			query.where(builder.equal(root.get("userid"), driverId));
			
			User user = getSession().createQuery(query).uniqueResult();

			commit();
			return user;
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Error while finding driver: " + e.getMessage());
		}
		
		
	}
	
	public void updateRequiresApproval(Long driverId, boolean requiresApproval) throws UserException {
	    try {
	        begin();
	        CriteriaBuilder builder = getSession().getCriteriaBuilder();
	        CriteriaUpdate<User> update = builder.createCriteriaUpdate(User.class);
	        Root<User> root = update.from(User.class);
	        
	        update.set("requiresApproval", requiresApproval); //takes two argument : the name of attribute to be updated, and new value to be assigned to that attribute
	        update.where(builder.equal(root.get("userid"), driverId));
	        
	        int updatedRows = getSession().createQuery(update).executeUpdate();
	        commit();
	        
	        System.out.println(updatedRows);
	        
	        if (updatedRows == 0) {
	            throw new UserException("No user found with driverId: " + driverId);
	        }
	    } catch (HibernateException e) {
	        rollback();
	        throw new UserException("Error while updating user requiresApproval: " + e.getMessage());
	    }
	}
	
	public void registerCarBrand(CarBrand carBrand) throws CarException{
		try {
			
			begin();
			getSession().persist(carBrand);
			commit();
			
		}catch(HibernateException e) {
			rollback();
			throw new CarException("Error while registering car: " +e.getMessage());
		}
	}
	
	

	public List<User> getUserByAccountHolderTypeAndCarIsNull(AccountHolderType driver, int userid) throws UserException{
		try {
			begin();
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			
			query.select(builder.construct(User.class,
					root.get("userid"),
					root.get("firstName"),
					root.get("lastName"),
					root.get("email"),
					root.get("phoneNo"),
					root.get("gender"),
					root.get("accountHolderType"),
					root.get("requiresApproval")
					));
			
////		query.where(builder.and(builder.equal(root.get("accountHolderType"), driver),
////					builder.equal(root.get("assignedToCar"), false)
////					));
////			
//			
			query.where(builder.and(
				    builder.equal(root.get("accountHolderType"), driver),
				    builder.isNull(root.get("car"))
				));

						
//			
				List<User> unassignedDriver = getSession().createQuery(query).getResultList();	
			System.out.println("I am unassigned: "+unassignedDriver);
			commit();
		
			return unassignedDriver;
		
		
		}catch (HibernateException e) {
	        rollback();
	        throw new UserException("Error while getting the driver List: " + e.getMessage());
	    }
	
	}

	public List<CarBrand> getAllCarBrand(String carBrand) throws CarException {
		
	
		try {
		begin();
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<CarBrand> query = builder.createQuery(CarBrand.class);
		Root<CarBrand> root = query.from(CarBrand.class);
		
		System.out.println("I am in carBrandDao above");
		query.select(builder.construct(CarBrand.class,
				root.get("carBrandId"),
				root.get("carBrandName")
				));
		
		// Select all properties of the CarBrand entity
		 query.select(root);
		
		 if(carBrand != null ){
		        query.where(builder.equal(root.get("carBrandName"), carBrand));
			
			 }
		
		List<CarBrand> carBrandList = getSession().createQuery(query).getResultList();
		System.out.println("I am in carBrandDao above: " +carBrandList);
		commit();
		return carBrandList;
		}catch(HibernateException e) {
			rollback();
			throw new CarException("Error while finding car:  +e.getMessage()");
		}
	
	}
	
	public void registerCars(Cars cars, User user) throws CarException{
		try {
			
			begin();
			System.out.println("I am herein registerCars");
			
//			if(cars.getCarBrand()!=null) {
//				System.out.println("I m in if condition");
//				CarBrand carBrand = getSession().get(CarBrand.class, cars.getCarBrand().getCarBrandId());
//				System.out.println(carBrand);
//				cars.setCarBrand(carBrand);
//				System.out.println(carBrand.getClass());
//			}
//			
//			if(cars.getDriver()!=null) {
//				User driver = getSession().get(User.class, cars.getDriver().getUserid());
//				System.out.println(driver);
//				cars.setDriver(driver);
//				System.out.println(driver.getClass());
//			}
			
//			if (cars.getCarBrand() != null) {
//			CarBrand carBrand  = getSession().get(CarBrand.class, cars.getCarBrand().getCarBrandId());
//			cars.setCarBrand(carBrand);
//			}else {
//	            throw new CarException("Car brand is not set");
//	        }
//			User driver = getSession().get(User.class, user.getUserid());
//			cars.setDriver(driver);
			
			getSession().persist(cars);
			commit();
			
			
		}catch(Exception e) {
			rollback();
			throw new CarException("Error while registering car:  +e.getMessage()");
		}
	}

	
	
	

	}

	

	
	
	
	


