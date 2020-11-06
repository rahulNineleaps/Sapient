package com.employee.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.employee.service.model.Employee;
import com.employee.service.repository.EmployeeRepository;


@Service
public class RepositoryService {

	@Autowired
	EmployeeRepository repository;
	@Cacheable(value="short-term-cache", key="'EmployeeCache'+employee.name+employee.salary+employee.title+employee.compentencies")
	public List<Employee> getAll() {
		return repository.findAll();
	}
	
	@CachePut(key="'EmployeeCache'+employee.name+employee.salary+employee.title+employee.compentencies")
	public void updateSalaryForPlce(String place, long increaseBy) {
		repository.updateEmployeeSalaryByPLace(place, increaseBy);
	}
	@Cacheable(value="short-term-cache", key="'EmployeeCache'+employee.name+employee.salary+employee.title+employee.compentencies")
	public List<Employee> getEmployeeByPlace(String place) {
		return repository.getEmployeeByPlace(place);
	}
	
	
	public List<Employee> getEmployeeByTitle(String title) {
		return repository.getEmployeeByTitle(title);
	}
	
	
	public Integer getTotalSalaryByBusinessUnit(String businessunit) {
		return repository.getTotalSalaryByBusinessUnit(businessunit);
	}
	
	public Integer getTotalSalaryBySupervisorId(long id) {
		return repository.getTotalSalaryBySupervisorId(id);
	}
	
	public Integer getTotalSalaryByPlace(String place) {
	return 	repository.getTotalSalaryByPlace(place);
	}

}
