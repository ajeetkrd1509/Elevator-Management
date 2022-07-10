package com.hcl.elevator.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Elevator  {
	@Id
	@Column
	private Integer elevatorId;
	@Column
	private Integer hotelId;
	@Column
	private Integer currentFloor;
	@Column	
	private Integer numberOfFloors;
	@Column
	@ElementCollection(targetClass = Integer.class)
	private Set<Integer> destinationFloors;
	@Column
	@ElementCollection(targetClass = Integer.class)
	private Set<Integer> pickupLocations;

	public Set<Integer> getPickupLocations() {
		return pickupLocations;
	}

	public void setPickupLocations(Set<Integer> pickupLocations) {
		this.pickupLocations = pickupLocations;
	}

	public Elevator(Integer elevatorId, Integer hotelId, Integer currentFloor) {
		this.elevatorId = elevatorId;
		this.hotelId = hotelId;
		this.currentFloor = currentFloor;
		this.destinationFloors = new HashSet<Integer>();
		this.pickupLocations= new HashSet<Integer>();
	}

	
	public Elevator(Integer elevatorId, Integer hotelId, Integer currentFloor, Integer numberOfFloors) {
		this.elevatorId = elevatorId;
		this.hotelId = hotelId;
		this.currentFloor = currentFloor;
		this.numberOfFloors = numberOfFloors;
		this.destinationFloors = new HashSet<Integer>();
		this.pickupLocations= new HashSet<Integer>();
	}

	public Elevator() {
		super();
	}

	public Integer getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(Integer numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public Integer getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(Integer elevatorId) {
		this.elevatorId = elevatorId;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(Integer currentFloor) {
		this.currentFloor = currentFloor;
	}
	
	public Elevator updateCurrentFloor(Integer currentFloor) {
		this.currentFloor = currentFloor;
		return this;
	}
	
	public Set<Integer> getDestinationFloors() {
		return destinationFloors;
	}

	public void setDestinationFloors(Set<Integer> destinationFloors) {
		this.destinationFloors = destinationFloors;
	}

	public int nextDestionation(Integer elevatorId, Integer hotelId) {
		return this.destinationFloors.iterator().next();
	}

	public int currentFloor(Integer elevatorId, Integer hotelId) {
		return this.currentFloor;
	}

}