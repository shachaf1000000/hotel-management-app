package com.springData.Hotel.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springData.Hotel.entities.Room;
import com.springData.Hotel.entities.Visitor;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	List<Room> findByOccupiedSinceIsNull();
	List<Room> findByOccupiedUntilLessThan(LocalDate date);
}
