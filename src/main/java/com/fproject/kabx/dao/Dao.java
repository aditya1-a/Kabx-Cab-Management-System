package com.fproject.kabx.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.fproject.kabx.util.HibernateUtil;

public class Dao {
	
	/*
	 Overall, this code initializes a logger for logging messages, a ThreadLocal object for 
	 storing thread-specific data, and a SessionFactory object for creating and managing 
	 database sessions in the application 
	 
	 */
	
	private static final Logger log = Logger.getAnonymousLogger();   //Logger object named log, which is used for logging purposes in the application. The getAnonymousLogger() method is called on the Logger class to create a logger with a generated name.
	private static final ThreadLocal sessionThread = new ThreadLocal(); //ThreadLocal is a way to associate a value with a thread, which can be useful for storing data that should be local to a specific thread.
	 private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); //create and manage database sessions in the application.
	
		protected Dao() {
	/*
	 constructor that is used to ensure that only subclasses of DAO can create new instances 
	 of the class. we are preventing any other class from creating an instance of the DAO class directly	
	 */
	}
	
	//returns hibernate session object
	public static Session getSession() {
		
		Session session = (Session) Dao.sessionThread.get(); //check if sessionThread variable contains  a session object
		
		if(session == null) {
			session = sessionFactory.openSession(); //open new session
			Dao.sessionThread.set(session);
		}
		return session;
	}
	
	protected void begin() {
		getSession().beginTransaction();
	}
	
	protected void commit() {
		getSession().getTransaction().commit();
	}
	
	protected void rollback() {
		try {
			getSession().getTransaction().rollback();			
		}catch(HibernateException e) {
			log.log(Level.WARNING, "cannot rollback", e);
		}
		try {
			getSession().close();
		} catch(HibernateException e) {
			log.log(Level.WARNING.ALL, "Cannot close", e);
		}
		Dao.sessionThread.set(null);
	}
	
	public static void close() {
		getSession().close();
		Dao.sessionThread.set(null);
	}
	
}
