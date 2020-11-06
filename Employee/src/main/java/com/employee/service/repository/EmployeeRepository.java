package com.employee.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.service.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	@Query("SELECT * FROM Employee e where e.place=:place")
	List<Employee> getEmployeeByPlace(@Param ("place") String place);
	
	
    @Query("SELECT * FROM Employee e where e.title=:title")
    List<Employee>getEmployeeByTitle(@Param ("title") String title);
    
    @Query("SELECT SUM(salary) from Employee e where e.businessunit=: businessunit")
    Integer getTotalSalaryByBusinessUnit(@Param ("businessunit") String businessunit);
    
    @Query("SELECT SUM(salary) from Employee e where e.SupervisorId=: SupervisorId")
    Integer getTotalSalaryBySupervisorId(@Param ("SupervisorId") Long SupervisorId);
    
    @Query("SELECT SUM(salary) from Employee e where e.Place=: Place")
    Integer getTotalSalaryByPlace(@Param ("Place") String Place);
    
    @Query("UPDATE Employee e SET e.salary = e.salary + :increaseBy where e.place=:place")
    void updateEmployeeSalaryByPLace(@Param ("place") String place, @Param("increaseBy") long increaseBy);
}
