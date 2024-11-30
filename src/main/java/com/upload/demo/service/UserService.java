package com.upload.demo.service;

import com.upload.demo.dto.UserRequest;
import com.upload.demo.dto.UserResponse;
import com.upload.demo.exception.NotFoundException;

public interface UserService {

	public UserResponse save(UserRequest userRequest) throws NotFoundException;
}
