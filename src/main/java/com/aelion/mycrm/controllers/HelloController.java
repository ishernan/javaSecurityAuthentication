package com.aelion.mycrm.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
	@GetMapping(produces="json")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.status(200).body("Hello Spring");
	}
}
