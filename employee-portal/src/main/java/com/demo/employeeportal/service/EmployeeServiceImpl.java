package com.demo.employeeportal.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employeeportal.messaging.MessageSender;
import com.demo.employeeportal.model.Employee;
import com.demo.employeeportal.model.EmployeeRegResponse;
import com.demo.employeeportal.repo.EmployeeRepository;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	private static final AtomicLong counter = new AtomicLong();
	
	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	MessageSender messageSender;

	@Transactional
	public List<Employee> findAllEmployees() {
		LOG.info("Inside findAllEmployees");
		return repository.findAll();
	}
	
	@Transactional
	public Employee findById(long id) {
		LOG.info("Inside findById");
		Optional<Employee> optional = repository.findById(id);
		if(optional.isPresent()) {
			Employee employee = optional.get();
			return employee;
		}
		return null;
	}
	
	@Transactional
	public Employee findByName(String firstName, String lastName) {
		LOG.info("Inside findByFirstName");
		List<Employee> list = repository.findAll();
		for(Employee employee : list){
			if(employee.getFirstname().equalsIgnoreCase(firstName) && employee.getLastname().equalsIgnoreCase(lastName)){
				return employee;
			}
		}
		return null;
	}
	
	@Transactional
	public void saveEmployee(Employee employee) {
		LOG.info("Inside saveEmployee");
		employee.setId(counter.incrementAndGet());
		employee.setStatus("Created");
		repository.save(employee);
		LOG.info("Sending employee register request {}", employee);
		messageSender.sendMessage(employee);
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}

	@Transactional
	public void updateEmployee(Employee employee) {
		LOG.info("Inside updateEmployee");
		repository.save(employee);
	}

	@Transactional
	public void deleteEmployeeById(long id) {
		LOG.info("Inside deleteEmployeeById");
		repository.deleteById(id);
	}

	@Transactional
	public boolean isEmployeeExist(Employee employee) {
		LOG.info("Inside isEmployeeExist");
		return findByName(employee.getFirstname(), employee.getLastname())!=null;
	}
	
	@Transactional
	public void deleteAllEmployees(){
		LOG.info("Inside deleteAllEmployees");
		repository.deleteAll();
	}
	
	@Transactional
	public void updatEmployee(EmployeeRegResponse response) {
		LOG.info("Inside updatEmployee");
		Optional<Employee> employee = repository.findById(response.getId());
		if (employee.isPresent()) {
			Employee emp = employee.get();
			if(response.getReturnCode()==200){
				emp.setStatus("Confirmed");
			}else if(response.getReturnCode()==300){
				emp.setStatus("Failed");
			}else{
				emp.setStatus("Pending");
			}
			repository.save(emp);
		}		
	}

}
