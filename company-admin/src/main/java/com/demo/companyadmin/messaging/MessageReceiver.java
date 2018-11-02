package com.demo.companyadmin.messaging;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.demo.companyadmin.service.EmpRegService;
import com.demo.employeeportal.model.Employee;

@Component
public class MessageReceiver {

	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
	private static final String QUEUE = "emp-queue";
	
	@Autowired
	EmpRegService empRegService;

	@JmsListener(destination = QUEUE)
	public void receiveMessage(final Message message) throws JMSException {
		LOG.info("----------------------------------------------------");
		MessageHeaders headers =  message.getHeaders();
		LOG.info("Headers received : {}", headers);
		
		Employee empReg  = (Employee)message.getPayload();		
		LOG.info("Employee details : {}",empReg);

		empRegService.processEmployeeReg(empReg);
		LOG.info("----------------------------------------------------");

	}
	
}
