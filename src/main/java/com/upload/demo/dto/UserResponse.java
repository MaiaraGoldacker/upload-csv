package com.upload.demo.dto;

import java.util.List;

import com.upload.demo.model.Role;

import lombok.Data;

@Data
public class UserResponse {
	
	private String username;
	
	private List<Role> roles;	
}
