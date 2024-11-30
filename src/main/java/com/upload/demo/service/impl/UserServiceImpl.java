package com.upload.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.upload.demo.dto.UserRequest;
import com.upload.demo.dto.UserResponse;
import com.upload.demo.exception.NotFoundException;
import com.upload.demo.exception.UnprocessableException;
import com.upload.demo.mapper.UserMapper;
import com.upload.demo.repository.UserRepository;
import com.upload.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public UserResponse save(UserRequest userRequest) throws NotFoundException {
		
		verifyIfExistsUsername(userRequest.getUsername());
		
		var user = userMapper.toUser(userRequest);
				
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		return userMapper.toUserResponse(userRepository.save(user));		
	}
	
	private void verifyIfExistsUsername(String username) {		
		var userOptional = userRepository.findByUsername(username);
		if (userOptional.isPresent()) {
			throw new UnprocessableException("Username Already Exists");
		}		
	}

}
