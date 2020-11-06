package com.employee.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="employee")
public class Employee {
	@Id
	@Column(name="EmployeeID")
	private long EmployeeID;
	
	@Column(name="EmployeeName")
	private String EmployeeName;
	
	@Column(name="Title")
	private String Title;
	
	@Column(name="BusinessUnit")
	private String BusinessUnit;
	
	@Column(name="Place")
	private String Place;
	
	@Column(name="SupervisorId")
	private long SupervisorId;
	
	@Column(name="Compentencies")
	private String Competencies;
	
	@Column(name="Salary")
	private double Salary;
	
	
}
