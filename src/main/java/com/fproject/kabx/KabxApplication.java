package com.fproject.kabx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan({"com.fproject.kabx.controller", "com.fproject.kabx.dao",
	"com.fproject.kabx.model", "com.fproject.kabx.exception", "com.fproject.kabx.util"})
public class KabxApplication {

	public static void main(String[] args) {
		 System.setProperty("server.servlet.context-path", "/kabx");
		SpringApplication.run(KabxApplication.class, args);
	}
	
	
	

}
