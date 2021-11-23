package com.exercise.phoneapplication;

import com.exercise.phoneapplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PhoneApplication {

	private final Logger logger = LoggerFactory.getLogger(PhoneApplication.class);

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PhoneApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void startup() {
		logger.info("\n--------\n" +
				"Phone Application startup complete. Use cURL or Postman to interact with the application.\n" +
				"Users: http://localhost:8080/api/v1/users \nPhones: http://localhost:8080/api/v1/phones" +
				"\n--------\n");
	}

}
