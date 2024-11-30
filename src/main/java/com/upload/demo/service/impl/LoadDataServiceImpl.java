package com.upload.demo.service.impl;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.upload.demo.exception.UnprocessableException;
import com.upload.demo.model.Form;
import com.upload.demo.service.FormService;
import com.upload.demo.service.LoadDataService;
import com.upload.demo.util.UploadUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class LoadDataServiceImpl implements LoadDataService {

	private final FormService formService;
	
	@Value("${parameters.path}")
	private String path;

	@Override
	public void loadData() {		
		try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(path))
				.withSkipLines(1)
				.build()) {
			
			String[] line;
			while ((line = csvReader.readNext()) != null) {		
				formService.save(buildForm(line), line[1]);              
			}
		} catch (CsvValidationException | IOException ex) {
			log.error("Error during import process: {}", ex.getMessage());
			throw new UnprocessableException("Error trying to Import csv in path ".concat(path));			
		}
	}

	private Form buildForm(String[] line) {
		return Form.builder()
			.source(line[0])
			.code(UploadUtils.isNullOrBlank(line[2])? "" : line[2].replaceAll("\\s+", ""))
			.displayValue(line[3])
			.longDescription(line[4])
			.fromDate(LocalDate.parse(line[5], UploadUtils.loadDateFormat()))
			.toDate(line[6].isEmpty()? null :  LocalDate.parse(line[6], UploadUtils.loadDateFormat()))
			.sortingPriority(line[7].isEmpty()? null : Integer.valueOf(line[7]))
			.build();
	}

}
