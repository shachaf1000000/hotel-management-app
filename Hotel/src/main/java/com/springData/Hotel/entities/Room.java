package com.springData.Hotel.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@GeneratedValue
	@Column(name = "room_number")
	private int roomNumber;
	
	@Column(name = "floor_number")
	@Min(value = 1, message = "floorNumber must be higher or equal to 1!")
	@Max(value = 20, message = "floorNumber must be lower or equal to 20!")

	private int floorNumber;
	@Column(name = "occupied_since", columnDefinition = "DATE")
	private LocalDate occupiedSince;
	@Column(name = "occupied_until", columnDefinition = "DATE")
	private LocalDate occupiedUntil;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "room")
	private List<Visitor> occupiedBy = new ArrayList<>();

	public Room() {
		super();
		
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		
			this.floorNumber = floorNumber;
	}

	public LocalDate getOccupiedSince() {
		return occupiedSince;
	}

	public void setOccupiedSince(LocalDate occupiedsince) {
		this.occupiedSince = occupiedsince;
	}

	public LocalDate getOccupiedUntil() {
		return occupiedUntil;
	}

	public void setOccupiedUntil(LocalDate occupieduntil) {
		this.occupiedUntil = occupieduntil;
	}

	public List<Visitor> getOccupiedBy() {
		return occupiedBy;
	}


	public void addVisitor(Visitor visitor) {
		if(occupiedBy.contains(visitor))
			return;
		occupiedBy.add(visitor);
	}
	public void removeVisitor(Visitor visitor) {
		
		occupiedBy.remove(visitor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roomNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return roomNumber == other.roomNumber;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", floorNumber=" + floorNumber + ", occupiedSince=" + occupiedSince
				+ ", occupiedUntil=" + occupiedUntil + "]";
	}

	public void setOccupiedBy(List<Visitor> visitors) {
		for (Visitor visitor : visitors) {
			addVisitor(visitor);
		}
	}

	

	


}
