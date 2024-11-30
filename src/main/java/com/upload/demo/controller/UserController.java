package com.upload.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upload.demo.dto.UserRequest;
import com.upload.demo.dto.UserResponse;
import com.upload.demo.exception.NotFoundException;
import com.upload.demo.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
    @PostMapping
    public ResponseEntity<UserResponse> post(@RequestBody UserRequest userRequest) throws NotFoundException {
    	return new ResponseEntity<>(userService.save(userRequest), HttpStatus.CREATED);        
    }
}
