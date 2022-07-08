package com.hcl.elevator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ElevatorManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElevatorManagementApplication.class, args);
	}
}
