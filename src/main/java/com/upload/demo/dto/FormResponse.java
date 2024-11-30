package com.upload.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FormResponse {

	private String source;	
	private String code;
	private String displayValue;
	private String longDescription;
	private LocalDate fromDate;
	private LocalDate toDate;
	private Integer sortingPriority;
    private LocalDateTime createdAt;
    private String codeList;
}
