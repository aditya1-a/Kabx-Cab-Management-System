package com.fproject.kabx.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

import com.fproject.kabx.model.User;
import com.fproject.kabx.model.Address;
import com.fproject.kabx.model.CarBrand;
import com.fproject.kabx.model.Cars;

@Component
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	public  static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/CabManagement");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Pass1234");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Address.class);
                configuration.addAnnotatedClass(Cars.class);
                configuration.addAnnotatedClass(CarBrand.class);
                
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                		applySettings(configuration.getProperties()).build();
                
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

}
