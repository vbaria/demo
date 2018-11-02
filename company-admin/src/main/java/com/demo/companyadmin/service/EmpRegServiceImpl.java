package com.demo.companyadmin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.companyadmin.messaging.MessageSender;
import com.demo.employeeportal.model.Employee;
import com.demo.employeeportal.model.EmployeeRegResponse;



@Service("empRegService")
public class EmpRegServiceImpl implements EmpRegService{
	static final Logger LOG = LoggerFactory.getLogger(EmpRegServiceImpl.class);
	
	@Autowired
	MessageSender messageSender;
	
	@Override
	public void processEmployeeReg(Employee empReg) {
		
		EmployeeRegResponse response = prepareResponse(empReg);
		LOG.info("Sending employee registration confirmation {}", response);
		messageSender.sendMessage(response);
	}
	
	private EmployeeRegResponse prepareResponse(Employee empReg){
		EmployeeRegResponse response = new EmployeeRegResponse();
		response.setId(empReg.getId());
		response.setReturnCode(200);
		response.setComment("Employee Registration Processed successfully");
		return response;
	}

	
	
}
