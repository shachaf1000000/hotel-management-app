package com.springData.Hotel.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springData.Hotel.entities.Visitor;
import com.springData.Hotel.repositories.VisitorRepository;

@RestController
@RequestMapping("visitors")
public class VisitorController {
	@Autowired
	private VisitorRepository visitorRepository;
	@GetMapping("all")
	public List<Visitor>getAll(){
		return visitorRepository.findAll();
	}
	@PostMapping("add")
	public ResponseEntity<?> addVisitor(Visitor visitor){
		Visitor visitorFromDB =visitorRepository.save(visitor);
		
		return new ResponseEntity<Visitor>(visitorFromDB, HttpStatus.OK);
		
	}
}
