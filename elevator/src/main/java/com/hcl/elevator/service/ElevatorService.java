package com.hcl.elevator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.elevator.model.Elevator;
import com.hcl.elevator.model.exceptions.InvalidNumber;
import com.hcl.elevator.repository.ElevatorRepository;

@Service
public class ElevatorService {
	
	public static final int MAX_ELEVATORS = 16;
	public static final int MAX_FLOORS = 10;
	public static final int MIN_FLOORS = -2;
	@Autowired
	ElevatorRepository elevatorRepository;


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

	public void saveOrUpdate(Elevator elevator) {
		elevatorRepository.save(elevator);
	}
	
	
	
	public void updateCurrentFloor(Integer floor,Integer id) {
		elevatorRepository.setCurrentFloorForElevator(floor, id);
	}
	
	
	public void pickUp(Elevator elevator) {
		elevatorRepository.save(elevator);
	}
	
	public void addElevator(Elevator elevator,Integer numberOfElevators) throws InvalidNumber  {
		
		if (numberOfElevators < 0)
			throw new InvalidNumber("Elevator number must be positive");
		if (elevator.getNumberOfFloors() > MAX_FLOORS)
			throw new InvalidNumber("Floor do not exits");
		List<Elevator> allElevators= getAllElevator();
		for (int idx = allElevators.size()+1; idx < numberOfElevators+allElevators.size()+1; idx++) {	
			elevatorRepository.save(new Elevator(idx,elevator.getHotelId(),0,elevator.getNumberOfFloors()));
		}
	}

	public void delete(int id) {
		elevatorRepository.deleteById(id);
	}
}