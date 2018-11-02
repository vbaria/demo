package com.demo.employeeportal.controller;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.employeeportal.model.Employee;
import com.demo.employeeportal.service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	static final Logger LOG = LoggerFactory.getLogger(EmployeeRestController.class);
 
    @Autowired
    EmployeeService employeeService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All Employees--------------------------------------------------------
     
    @RequestMapping(value = "/employee/", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllEmployees() {
    	LOG.info("Inside listAllEmployees");
        List<Employee> employees = employeeService.findAllEmployees();
        if(employees.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single Employee--------------------------------------------------------
     
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) throws Exception {
    	LOG.info("Fetching Employee with id " + id);
        Employee employee = employeeService.findById(id);
        if (employee == null) {
        	LOG.info("Employee with id " + id + " not found");
           // return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        	throw new Exception("Employee with id " + id + " not found");
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Employee--------------------------------------------------------
     
    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder){
    	LOG.info("Creating Employee " + employee.getFirstname());
 
        if (employeeService.isEmployeeExist(employee)) {
        	LOG.info("A Employee with name " + employee.getFirstname() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);      	
        }
 
        employeeService.saveEmployee(employee);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a Employee --------------------------------------------------------
     
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
    	LOG.info("Updating Employee " + id);
         
        Employee currentEmployee = employeeService.findById(id);
         
        if (currentEmployee==null) {
        	LOG.info("Employee with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
 
        currentEmployee.setFirstname(employee.getFirstname());
        currentEmployee.setLastname(employee.getLastname());
        currentEmployee.setEmail(employee.getEmail());
         
        employeeService.updateEmployee(currentEmployee);
        return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a Employee --------------------------------------------------------
     
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) {
    	LOG.info("Fetching & Deleting Employee with id " + id);
 
        Employee employee = employeeService.findById(id);
        if (employee == null) {
        	LOG.info("Unable to delete. Employee with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
 
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Employees --------------------------------------------------------
     
    @RequestMapping(value = "/employee/", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteAllEmployees() {
    	LOG.info("Deleting All Employees");
 
        employeeService.deleteAllEmployees();
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
 
}