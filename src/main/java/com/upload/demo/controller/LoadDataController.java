package com.upload.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.upload.demo.service.LoadDataService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@SecurityRequirement(name = "basicAuth")
@RequestMapping(value = "/load")
public class LoadDataController {
	
	@Autowired
	private LoadDataService service;

	@PreAuthorize("hasRole('MANAGE_FORM')")
    @PostMapping("/import")
	@ResponseStatus(HttpStatus.OK)
    public void loadData() {
    	service.loadData();
    
    }
}
