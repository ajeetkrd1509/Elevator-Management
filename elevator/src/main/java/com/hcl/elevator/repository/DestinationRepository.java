package com.hcl.elevator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.elevator.model.Destination;  
@Repository
@Transactional
public interface DestinationRepository extends JpaRepository<Destination, Integer>  
{  
	Iterable<Destination> findByElevatorId(Integer elevator);  
	Iterable<Destination> findByHotelId(Integer hotelId);  
}  
