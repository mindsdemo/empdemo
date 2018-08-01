package com.mindtree.easybucks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationBootStartup {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBootStartup.class, args);
		System.out.println("====>Good <====");
	}
}
