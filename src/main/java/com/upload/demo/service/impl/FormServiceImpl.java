package com.upload.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.upload.demo.dto.FormResponse;
import com.upload.demo.exception.NotFoundException;
import com.upload.demo.exception.UnprocessableException;
import com.upload.demo.mapper.FormMapper;
import com.upload.demo.model.Form;
import com.upload.demo.repository.FormRepository;
import com.upload.demo.service.FormService;
import com.upload.demo.service.TypeFormService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
	
	private final FormRepository formRepository;
	
	private final TypeFormService typeFormService;
	
	private final FormMapper formMapper;

	@Transactional
	@Override
	public Form save(Form form, String typeForm) {
		form.setCodeList(typeFormService.getOrSaveTypeFormByCode(typeForm));
		
		validate(form);
		
		return formRepository.save(form);
	}
	
	@Override
	public FormResponse getByCode(String code) {
		var form = formRepository.findByCode(code).orElseThrow(() 
				-> new NotFoundException("Code not found"));
		
		return formMapper.toFormResponse(form);
	}
	
	@Override
	public List<FormResponse> getAll() {		
		return formRepository.findAll().stream().map(
				form -> formMapper.toFormResponse(form)).toList();		
	}
	
	@Override
	public void deleteAll() {
		formRepository.deleteAll();
	}
	
	private void validate(Form form) {
		
		if (form.getCode().isBlank()) {
			throw new UnprocessableException("Code can't be empty");
		}
		
		var optionalForm = formRepository.findByCode(form.getCode());
		if (optionalForm.isPresent()) {
			throw new UnprocessableException("Code "
					.concat(optionalForm.get().getCode())
					.concat(" already exists and can't be the same"));
		}
	}	

}
