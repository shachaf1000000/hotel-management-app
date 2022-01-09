package com.springData.Hotel.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springData.Hotel.entities.Room;
import com.springData.Hotel.repositories.RoomRepository;

@RestController
@RequestMapping("rooms")
public class RoomController {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@GetMapping("all")
	public List<Room>getAll(){
		return roomRepository.findAll();
	}
	@PostMapping("add")
	public ResponseEntity<?> addRoom(Room room){
		if (room.getFloorNumber()<1||room.getFloorNumber()>20) {
			return new ResponseEntity<String>("Invalid FloorNumber "+room.getFloorNumber(), HttpStatus.BAD_REQUEST);
		}
		Room roomFromDB = roomRepository.save(room);
		
		return new ResponseEntity<Room>(roomFromDB, HttpStatus.OK);
	}
}
