package com.hcl.elevator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Destination {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@Column
	private Integer hotelId;
	@Column
	private Integer elevatorId;
	@Column
	private Integer elevatorFloor;

	public Destination() {
		super();

	}

	public Destination(Integer hotelId, Integer elevatorId, Integer elevatorFloor) {
		super();
		this.hotelId = hotelId;
		this.elevatorId = elevatorId;
		this.elevatorFloor = elevatorFloor;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(Integer elevatorId) {
		this.elevatorId = elevatorId;
	}

	public Integer getElevatorFloor() {
		return elevatorFloor;
	}

	public void setElevatorFloor(Integer elevatorFloor) {
		this.elevatorFloor = elevatorFloor;
	}

}
