package com.upload.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upload.demo.model.Form;

import java.util.Optional;


public interface FormRepository extends JpaRepository<Form, Long> {
	
	Optional<Form> findByCode(String code);

}
