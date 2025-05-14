package com.akib.courseManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Course Management System.
 * This class serves as the entry point for the Spring Boot application,
 * initializing the Spring context and starting the application.
 */
@SpringBootApplication
public class CourseManagementSystemApplication {

	/**
	 * The main method that starts the Spring Boot application.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		// Bootstrap the Spring application context
		SpringApplication.run(CourseManagementSystemApplication.class, args);
	}
}