package com.upload.demo.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.upload.demo.dto.FormResponse;
import com.upload.demo.model.Form;

@Component
@RequiredArgsConstructor
public class FormMapper {

	@Autowired
    private ModelMapper modelMapper;

    public FormResponse toFormResponse(Form form) {
    	var formResponse = modelMapper.map(form, FormResponse.class);  
    	formResponse.setCodeList(form.getCodeList().getCode());
    	return formResponse;
    }

}