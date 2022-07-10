package com.hcl.elevator.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.elevator.model.Destination;
import com.hcl.elevator.model.Elevator;
import com.hcl.elevator.model.exceptions.InvalidNumber;
import com.hcl.elevator.service.DestinationService;
import com.hcl.elevator.service.ElevatorService;

@RestController
public class ElevatorController {
	@Autowired
	ElevatorService elevatorService;
	@Autowired
	DestinationService destinationService;
	
	@Value("${app.elevator.accesscode}")
	String elevator_accesscode;
	@Value("${app.elevator.security.validation.failed.msg}")
	String security_validation_failed_msg;
	@Value("${app.elevator.security.validation.pass.msg}")
	String security_validation_pass_msg;
	
	@Value("${app.elevator.private}")
	private List<String> privateElevatorList;

	@GetMapping("/elevator")
	private List<Elevator> getAllElevator() {
		return elevatorService.getAllElevator();
	}

	@GetMapping("/destination")
	private List<Destination> getAllDestination() {
		return destinationService.getAllDestination();
	}

	@GetMapping("/elevator/{id}")
	private Elevator getElevator(@PathVariable("id") int id) {
		return elevatorService.getElevatorById(id);
	}

	@GetMapping("/hotel/{id}")
	private List<Elevator> getHotel(@PathVariable("id") int id) {
		return elevatorService.getAllHotelById(id);
	}

	@DeleteMapping("/elevator/{id}")
	private void deleteElevator(@PathVariable("id") int id) {
		elevatorService.delete(id);
	}

	@PostMapping("/elevator")
	private int saveElevator(@RequestBody Elevator elevator) {
		elevatorService.save(elevator);
		return elevator.getElevatorId();
	}

	@PutMapping("/elevator/pickup")
	private String pickup(@RequestBody Elevator elevator, @RequestHeader("accessCode") String accessCode) {
		if (securityCheck(elevator, accessCode) == -1) {
			return security_validation_failed_msg;
		}
		elevatorService.save(elevator);
		int pickupLocation = elevator.getPickupLocations().iterator().next();
		elevatorService.updateFloors(pickupLocation, elevator);
		return security_validation_pass_msg;
	}

	@PutMapping("/elevator/destination")
	private String addDestination(@RequestBody Elevator elevator, @RequestHeader("accessCode") String accessCode) {
		if (securityCheck(elevator, accessCode) == -1) {
			return security_validation_failed_msg;
		}
		elevatorService.save(elevator);
		int pickupLocation = elevator.getDestinationFloors().iterator().next();
		elevatorService.updateFloors(pickupLocation, elevator);
		destinationService.save(new Destination(elevator.getHotelId(), elevator.getElevatorId(),
				elevator.getDestinationFloors().iterator().next()));
		return security_validation_pass_msg;
	}
	
	private int securityCheck(Elevator elevator, String accessCode) {
		if (privateElevatorList.contains(elevator.getElevatorId().toString())) {
			if (!accessCode.equals(elevator_accesscode)) {
				return -1;
			}
		}
		return 0;
	}
	@PostMapping("/newElevator")
	private String addNewHotelElevator(@RequestBody Elevator elevator,
			@RequestParam(defaultValue = "1") Optional<Integer> numberOfElevators) throws InvalidNumber {
		return elevatorService.addElevator(elevator, numberOfElevators.get());
	}

}