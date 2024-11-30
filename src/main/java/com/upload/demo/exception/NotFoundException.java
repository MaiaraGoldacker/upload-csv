package com.upload.demo.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.upload.demo.dto.ErrorResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	 private ErrorResponseDTO errorResponse;

	public NotFoundException(String message) {
		super(message);
		 this.errorResponse = ErrorResponseDTO.builder()
	                .status(HttpStatus.NOT_FOUND.value())
	                .errors(List.of(message)).build();
	}
 
}
