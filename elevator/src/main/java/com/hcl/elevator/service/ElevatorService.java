package com.hcl.elevator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hcl.elevator.model.Elevator;
import com.hcl.elevator.model.exceptions.InvalidNumber;
import com.hcl.elevator.repository.ElevatorRepository;

@Service
public class ElevatorService {
	
	@Autowired
	ElevatorRepository elevatorRepository;
		
	@Value("${app.elevator.max.numbers}")
	Integer max_elevators;
	@Value("${app.elevator.max.floors}")
	Integer max_floors;
	@Value("${app.elevator.min.floors}")
	Integer min_floors;

	public List<Elevator> getAllElevator() {
		List<Elevator> elevators = new ArrayList<Elevator>();
		elevatorRepository.findAll().forEach(elevator -> elevators.add(elevator));
		return elevators;
	}
	
	public List<Elevator> getAllHotelById(Integer hotelId) {
		List<Elevator> elevators = new ArrayList<Elevator>();
		elevatorRepository.findByHotelId(hotelId).forEach(elevator -> elevators.add(elevator));
		return elevators;
	}

	public Elevator getElevatorById(int id) {
		return elevatorRepository.findById(id).get();
	}

	public void save(Elevator elevator) {
		elevatorRepository.save(elevator);
	}
	
	public void updateFloors(Integer floor, Elevator elevator) {
		elevatorRepository.setCurrentFloorForElevator(floor,elevator.getElevatorId());
	}
	
	public String addElevator(Elevator elevator,Integer numberOfElevators) throws InvalidNumber  {
		if (numberOfElevators < 0)
			throw new InvalidNumber("Elevator number must be positive");
		if (this.getAllHotelById(elevator.getHotelId()).size() > max_elevators)
			throw new InvalidNumber("Number of Elevator exceeds the allowed limit ");
		if (elevator.getNumberOfFloors() > max_floors)
			throw new InvalidNumber("Floor do not exits");
		if (elevator.getNumberOfFloors() < min_floors)
			throw new InvalidNumber("Floor do not exits");
		List<Elevator> allElevators= getAllElevator();
		for (int idx = allElevators.size()+1; idx < numberOfElevators+allElevators.size()+1; idx++) {	
			elevatorRepository.save(new Elevator(idx,elevator.getHotelId(),0,elevator.getNumberOfFloors()));
		}
		return numberOfElevators + " new Elevators Added";
	}

	public void delete(int id) {
		elevatorRepository.deleteById(id);
	}
}