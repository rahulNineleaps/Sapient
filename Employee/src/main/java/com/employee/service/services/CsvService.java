package com.employee.service.services;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.service.model.Employee;
import com.employee.service.repository.EmployeeRepository;

@Service
public class CsvService {
	@Autowired
	static	EmployeeRepository repository;
	
	public static void save ()throws IOException {
		List<Employee> empList=CsvHelper.csvToEmployee();
		repository.saveAll(empList);
	}

	
	@PostConstruct
	public void uploadOnStart() throws IOException{
		CsvService.save();
	}
}
