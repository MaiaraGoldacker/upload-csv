package com.upload.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name="type_form")
@AllArgsConstructor
@NoArgsConstructor
public class TypeForm {
	
	@Id
	private String code;
	
	private String description;

}
