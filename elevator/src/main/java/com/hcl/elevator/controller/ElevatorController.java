package com.hcl.elevator.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		elevatorService.saveOrUpdate(elevator);
		return elevator.getElevatorId();
	}
	
	@PutMapping("/elevator/pickup")
	private int pickup(@RequestBody Elevator elevator) {
		elevatorService.saveOrUpdate(elevator);
		int pickupLocation = elevator.getPickupLocations().iterator().next();
		elevatorService.updateCurrentFloor(pickupLocation,elevator.getElevatorId());
		return elevator.getElevatorId();
	}
	
	@PutMapping("/elevator/destination")
	private int addDestination(@RequestBody Elevator elevator) {
		elevatorService.saveOrUpdate(elevator);
		int pickupLocation = elevator.getDestinationFloors().iterator().next();
		elevator.updateCurrentFloor(pickupLocation);
		elevatorService.updateCurrentFloor(pickupLocation,elevator.getElevatorId());
		destinationService.save(new Destination(elevator.getHotelId(),elevator.getElevatorId(),elevator.getDestinationFloors().iterator().next()));
		return elevator.getElevatorId();
	}
		
	@PostMapping("/newElevator")
	private int addNewHotelElevator(@RequestBody Elevator elevator, @DefaultValue("1") @RequestParam Optional<Integer> numberOfElevators) throws InvalidNumber {
		elevatorService.addElevator(elevator, numberOfElevators.get());
		return elevator.getElevatorId();
	}
	
}