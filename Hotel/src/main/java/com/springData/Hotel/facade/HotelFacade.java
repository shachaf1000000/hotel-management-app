package com.springData.Hotel.facade;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springData.Hotel.entities.Room;
import com.springData.Hotel.entities.Visitor;
import com.springData.Hotel.repositories.RoomRepository;
import com.springData.Hotel.repositories.VisitorRepository;

@Service
@Transactional
public class HotelFacade {
	private final int CHILED_AGE_FROM = 2;
	private final int CHILED_AGE_TO = 12;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private VisitorRepository visitorRepository;

	public HotelFacade() {
		super();
	}

	public void addRoom(Room room) {
		roomRepository.save(room);
	}
	
	public void addVisitor(Visitor visitor, Room room) {
		room.addVisitor(visitor);
		visitor.setRoom((long) room.getRoomNumber());
		// Saving to database using CRUD Repository
		roomRepository.save(room);
		
	}

	// 4.a Done
	public boolean isOccupied(Room room) {
		Room localRoom = roomRepository.getById(room.getRoomNumber());
		if (localRoom.getOccupiedSince() == null) {
			return false;
		}
		return true;
	}

	// 4.b Done
	public List<Room> getAllAvailableRooms() {
		return roomRepository.findByOccupiedSinceIsNull();
	}

	// 4.c Done
	public List<Room> getAllAvailableRoomsTomorrow(LocalDate date) {
		return roomRepository.findByOccupiedUntilLessThan(date);
	}

	// 4.d Done
	public void addFamaliyToAvaliableRoom(List<Visitor> visitors, LocalDate start, LocalDate end) {
		List<Room> rooms = getAllAvailableRooms();
		Room room = rooms.get(0);
		room.setOccupiedBy(visitors);
		room.setOccupiedSince(start);
		room.setOccupiedUntil(end);
		roomRepository.save(room);
	}

	public Room getRoom(int id) {
		return roomRepository.getById(id);
	}

	public List<Visitor> getVisitor(String lastName, String firstName) {
		return visitorRepository.findByLastNameOrFirstName(lastName, firstName);
	}

	public Integer getNumOfChildren() {
		return visitorRepository.countByBirthdayBetween(LocalDate.now().minusYears((CHILED_AGE_FROM) - 1),
				LocalDate.now().minusYears((CHILED_AGE_TO) + 1));
	}
}
