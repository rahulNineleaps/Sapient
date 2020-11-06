package com.employee.service.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.employee.service.model.Employee;

public class CsvHelper {
	public static String TYPE="text/csv";
	static String Headers[]= {"EmployeeID", "EmployeeName", "Title","BusinessUnit", "Place" ,"SupervisorID", "Competencies",
			"Salary"};

	 
	  public static List<Employee> csvToEmployee() {
		    try (BufferedReader fileReader = new BufferedReader(new FileReader("employee.csv"));
		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

		      List<Employee> employees = new ArrayList<Employee>();

		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

		      for (CSVRecord csvRecord : csvRecords) {
		       Employee emp = new Employee( Long.parseLong(csvRecord.get("EmployeeID")), csvRecord.get("EmployeeName"), csvRecord.get("Title"), csvRecord.get("BusinessUnit"), csvRecord.get("Place"),
		    		   Long.parseLong(csvRecord.get("SupervisorId")), csvRecord.get("Competencies"), Double.parseDouble(csvRecord.get("Salary")));

		       employees.add(emp);
		      }

		      return employees;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
		  }
	}

