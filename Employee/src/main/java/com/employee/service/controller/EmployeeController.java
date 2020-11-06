package com.employee.service.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.service.dto.TitleDto;
import com.employee.service.model.Employee;
import com.employee.service.repository.EmployeeRepository;
import com.employee.service.services.CsvService;
import com.employee.service.services.RepositoryService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	CsvService fileService;

	@Autowired
	RepositoryService repositoryservice;

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> empList = repositoryservice.getAll();
		if (empList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(empList, HttpStatus.OK);

	}

	@GetMapping("/place/{place}")
	public ResponseEntity<List<Employee>> getEmployeeByPlace(@PathParam("place") String place) {
		List<Employee> empList = repositoryservice.getEmployeeByPlace(place);
		if (empList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(empList, HttpStatus.OK);

	}

	@GetMapping("/title/{title}")
	public ResponseEntity getSalaryForTitle(@PathParam("title") String title) {
		List<Employee> empList = repositoryservice.getEmployeeByTitle(title);
		if (empList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Map<String, Double> empmap = empList.stream()
				.collect(Collectors.groupingBy(Employee::getTitle, Collectors.averagingDouble(Employee::getSalary)));
		TitleDto result = new TitleDto(title, empmap.get(title));

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/total/BU/{BU}")
	public ResponseEntity<Integer> GetTotalSalaryByBusinessUnit(@PathParam("BU") String BU) {
		// Integer result = 0;
		Integer returnVal = (repositoryservice.getTotalSalaryByBusinessUnit(BU));

		return new ResponseEntity<Integer>(returnVal, HttpStatus.OK);
	}

	@GetMapping("/total/supervisorId/{id}")
	public ResponseEntity<Integer> GetTotalSalaryByBusinessUnit(@PathParam("id") Long id) {
		Integer result = 0;
		Integer returnVal = repositoryservice.getTotalSalaryBySupervisorId(id);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/total/place/{place}")
	public ResponseEntity<Integer> GetTotalSalaryByPlace(@PathParam("place") String place) {
		Integer result = 0;
		Integer returnVal = repositoryservice.getTotalSalaryByPlace(place);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@PutMapping("/place/{place}/salary/{percent}")
	public ResponseEntity updateSalaryForPlace(@PathParam("place") String place, @PathParam("percent") Long percent) {
		repositoryservice.updateSalaryForPlce(place, percent);
		return new ResponseEntity(HttpStatus.OK);
	}

}
