package com.hcl.elevator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.elevator.model.Elevator;  
@Repository
@Transactional
public interface ElevatorRepository extends JpaRepository<Elevator, Integer>  
{

	Iterable<Elevator> findByHotelId(Integer hotelId);  
	
	@Modifying
	@Query("update Elevator e set e.currentFloor = :floor where e.elevatorId = :id")
	int setCurrentFloorForElevator(@Param(value = "floor") int floor,@Param(value = "id") int id);
	

}  
