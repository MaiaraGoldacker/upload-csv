package com.upload.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.upload.demo.dto.FormResponse;
import com.upload.demo.service.FormService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "basicAuth")
@RequestMapping(value = "/form")
public class FormController {

	@Autowired
	private FormService formService;
	
	@PreAuthorize("hasRole('CONSULT_FORM')")
    @GetMapping
    public ResponseEntity<List<FormResponse>> getAll() {		
		return new ResponseEntity<>(formService.getAll(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('CONSULT_FORM')")
    @GetMapping("/{code}")
    public ResponseEntity<FormResponse> getByCode(@PathVariable("code") String code) {		
		return new ResponseEntity<>(formService.getByCode(code), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('MANAGE_FORM')")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
		formService.deleteAll();
    
	}
}
