package com.springData.Hotel.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springData.Hotel.entities.Visitor;
import com.sun.jdi.Value;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
	List<Visitor> findByLastNameOrFirstName( String firstName,String lastName);
	
	Integer countByBirthdayBetween( LocalDate startDate ,LocalDate endDate);

}
