package com.mindtree.easybucks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * SpringBootServletInitializer : Required for External Tomcat/WebContaier war deloyment 
 * @author M1046249
 *
 */
@SpringBootApplication
public class ApplicationBootStartup extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBootStartup.class, args);
		System.out.println("====>Good <====");
	}
	
	
	
	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder builder) {
		
		return builder.sources(ApplicationBootStartup.class);
	}
}
