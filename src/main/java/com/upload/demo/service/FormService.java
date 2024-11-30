package com.upload.demo.service;

import java.util.List;

import com.upload.demo.dto.FormResponse;
import com.upload.demo.model.Form;

public interface FormService {

	Form save(Form buildForm, String TypeForm);
	
	FormResponse getByCode(String code);
	
	List<FormResponse> getAll();
	
	void deleteAll();

}
