package com.upload.demo.service.impl;

import org.springframework.stereotype.Service;

import com.upload.demo.model.TypeForm;
import com.upload.demo.repository.TypeFormRepository;
import com.upload.demo.service.TypeFormService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeFormServiceImpl implements TypeFormService {

	private final TypeFormRepository typeFormRepository;
	
	@Override
	public TypeForm getOrSaveTypeFormByCode(String code) {
		var typeFormOptional = typeFormRepository.findById(code);
		
		if (typeFormOptional.isPresent()) {
			return typeFormOptional.get();
		}
		
		return typeFormRepository.save(TypeForm.builder().code(code).build());	
	}
}
