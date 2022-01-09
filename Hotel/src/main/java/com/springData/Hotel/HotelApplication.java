package com.springData.Hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.format.datetime.joda.LocalDateParser;

import com.springData.Hotel.entities.Room;
import com.springData.Hotel.entities.Visitor;
import com.springData.Hotel.facade.HotelFacade;
import com.springData.Hotel.repositories.RoomRepository;
import com.springData.Hotel.repositories.VisitorRepository;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(HotelApplication.class, args);
		HotelFacade hotelFacade = ctx.getBean(HotelFacade.class);
		System.out.println("spring is running");
		Room r1 = ctx.getBean(Room.class);
		Room r2 = ctx.getBean(Room.class);
		Room r3 = ctx.getBean(Room.class);

		Visitor v1 = ctx.getBean(Visitor.class);
		Visitor v2 = ctx.getBean(Visitor.class);
		Visitor v3 = ctx.getBean(Visitor.class);
		Visitor v4 = ctx.getBean(Visitor.class);

		r1.setFloorNumber(1);
		r1.setOccupiedSince(LocalDate.now().minusDays(1));
		r1.setOccupiedUntil(LocalDate.now().plusDays(1));

		r2.setFloorNumber(2);
		r2.setOccupiedSince(LocalDate.now().minusDays(2));
		r2.setOccupiedUntil(LocalDate.now());

		r3.setFloorNumber(3);

		v1.setBirthday(LocalDate.now().minusYears(10));
		v1.setFirstName("Itay");
		v1.setLastName("Mazor");

		v2.setBirthday(LocalDate.now().minusYears(15));
		v2.setFirstName("Shem");
		v2.setLastName("tov");

		v3.setBirthday(LocalDate.now().minusYears(40).minusMonths(5));
		v3.setFirstName("Rami");
		v3.setLastName("Younes");

		v4.setBirthday(LocalDate.now().minusYears(34));
		v4.setFirstName("Yefet");
		v4.setLastName("San martin");
//		System.out.println(v1.toString());
		hotelFacade.addVisitor(v1, r1);
		hotelFacade.addVisitor(v2, r2);
		hotelFacade.addVisitor(v3, r2);
		hotelFacade.addVisitor(v4, r1);
		hotelFacade.addRoom(r3);
		
		System.out.println("Hotel 4.A is room Occupied?");
		Room r4 = hotelFacade.getRoom(1);
		System.out.println(hotelFacade.isOccupied(r4));
		System.out.println("---------------------------------");
		System.out.println("Hotel 4.b All rooms I can use");
		System.out.println(hotelFacade.getAllAvailableRooms());
		System.out.println("---------------------------------");
		System.out.println("Hotel 4.c all rooms I can use tomorrow");
		System.out.println(hotelFacade.getAllAvailableRoomsTomorrow(LocalDate.now().plusDays(1)));
		System.out.println("---------------------------------");
		System.out.println("Hotel 4.d (check database)");
		Visitor v5 = ctx.getBean(Visitor.class);
		Visitor v6 = ctx.getBean(Visitor.class);
		v5.setBirthday(LocalDate.now().minusYears(10));
		v5.setFirstName("Moti");
		v5.setLastName("Lohim");
		v6.setBirthday(LocalDate.now().minusYears(15));
		v6.setFirstName("Avi");
		v6.setLastName("Ron");
		List<Visitor> famaliyList = new ArrayList<>();
		famaliyList.add(v5);
		famaliyList.add(v6);
		hotelFacade.addFamaliyToAvaliableRoom(famaliyList, LocalDate.now().plusDays(2), LocalDate.now().plusDays(4));
		System.out.println(famaliyList.size());
		System.out.println("---------------------------------");
//		ctx.close();
	}
}
