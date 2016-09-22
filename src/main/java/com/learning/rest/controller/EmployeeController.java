package com.learning.rest.controller;
 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.rest.bean.Employee;
 
@RestController
@RequestMapping(value = "/Employee")
public class EmployeeController {
 
	@RequestMapping(value = "/{name}/{emailId:.+}", method = RequestMethod.GET)
	public Employee process(
			@PathVariable("name") String name,
			@PathVariable("emailId") String emailId,
			@RequestParam(value = "id", required = false, defaultValue = "99999") final String id) {
		Employee employee = new Employee();
		employee.setEmailId(emailId);
		employee.setEmpId(id);
		employee.setName(name);
		return employee;
	}
}