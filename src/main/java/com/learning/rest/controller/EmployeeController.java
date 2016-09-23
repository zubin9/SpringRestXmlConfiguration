package com.learning.rest.controller;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.rest.bean.Employee;
 
// http://localhost:8080/RestMavenSpringMVC/API/Employee/Zubin/zubin095@gmail.com?id=1712
// http://localhost:8080/RestMavenSpringMVC/API/Employee/Zubin.xml/zubin095@gmail.com?id=1712
// http://localhost:8080/RestMavenSpringMVC/API/Employee/populateDataFromServer/Zubin
//If XMLRootElement is not used in model class, then by default it's JSON.
//We can also control xml vs json from produces parameter of requestMapping


@RestController
@RequestMapping(value = "/Employee")
public class EmployeeController {
 
	@RequestMapping(value = "/{name}/{emailId:.+}", method = RequestMethod.GET, produces = "application/json")
	public Employee process(
			@PathVariable("name") String name,
			@PathVariable("emailId") String emailId,
			@RequestParam(value = "id", required = false, defaultValue = "00000") final String id) {
		Employee employee = new Employee();
		employee.setEmailId(emailId);
		employee.setEmpId(id);
		employee.setName(name);
		return employee;
	}
	
	@RequestMapping(value = "/{name}.xml/{emailId:.+}", method = RequestMethod.GET, produces = "application/xml")
	public Employee processXml(
			@PathVariable("name") String name,
			@PathVariable("emailId") String emailId,
			@RequestParam(value = "id", required = false, defaultValue = "00000") final String id) {
		Employee employee = new Employee();
		employee.setEmailId(emailId);
		employee.setEmpId(id);
		employee.setName(name);
		return employee;
	}
	
	@RequestMapping(value = "/populateDataFromServer/{name}", method=RequestMethod.PUT, produces={"application/json"})
	//@ResponseBody : This annotation is optional with Spring-4
	public List<Integer> testRestPUT(@PathVariable String name) {
		if (name.equalsIgnoreCase("Zubin")) {
			System.out.println("in the if loop : put method");
			return returnDataList();
		}else {
			System.out.println("in the else loop : put method");
			List<Integer> list = new ArrayList<Integer>();
			list.add(12345);
			list.add(1712);			
			return list;
		}
	}
	
	@RequestMapping(value = "/populateDataFromServer/{name}", method=RequestMethod.DELETE, produces={"application/json"})
	public List<Integer> deleteDemo(@PathVariable String name) {
		if (name.equalsIgnoreCase("abcd")) {
			System.out.println("in the if loop : delete method");
			return returnDataList();
		}else {
			System.out.println("in the else loop : delete method");
			List<Integer> list = new ArrayList<Integer>();
			list.add(98765);
			list.add(4785);			
			return list;
		}
	}
	
	private List<Integer> returnDataList() {
		Random rand = new Random();
	    Integer randomNum = rand.nextInt();		
		List<Integer> list = new ArrayList<Integer>();
		list.add(randomNum);		
		return list;
	}
}