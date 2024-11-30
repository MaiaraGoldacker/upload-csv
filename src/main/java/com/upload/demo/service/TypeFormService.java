package com.upload.demo.service;

import com.upload.demo.model.TypeForm;

public interface TypeFormService {

	TypeForm getOrSaveTypeFormByCode(String code); 

}
