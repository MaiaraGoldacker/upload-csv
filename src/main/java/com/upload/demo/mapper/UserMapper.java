package com.upload.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.upload.demo.dto.UserRequest;
import com.upload.demo.dto.UserResponse;
import com.upload.demo.exception.NotFoundException;
import com.upload.demo.model.User;
import com.upload.demo.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private RoleRepository roleRepository;

    public User toUser(UserRequest request) throws NotFoundException {    
    	var user = modelMapper.map(request, User.class); 
    	
    	for (var id : request.getRolesId()) {
    		var optionalRole = roleRepository.findById(id);
        	
        		if (optionalRole.isEmpty()) {
        			throw new NotFoundException("Role not found");
        		}
        		user.addRole(optionalRole.get());    	
        	}

    	return user;
    }

	public UserResponse toUserResponse(User user) {
		var userResponse = modelMapper.map(user, UserResponse.class); 
		userResponse.setRoles(user.getRoles());
		return userResponse;
	}

}
