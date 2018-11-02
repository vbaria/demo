package com.demo.employeeportal.messaging;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.demo.employeeportal.model.EmployeeRegResponse;
import com.demo.employeeportal.service.EmployeeService;


@Component
public class MessageReceiver {
	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	private static final String RESPONSE_QUEUE = "emp-response-queue";
	
	@Autowired
	EmployeeService empService;
	
	
	@JmsListener(destination = RESPONSE_QUEUE)
	public void receiveMessage(final Message message) throws JMSException {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		MessageHeaders headers =  message.getHeaders();
		LOG.info("headers received : {}", headers);
		
		EmployeeRegResponse response = (EmployeeRegResponse) message.getPayload();
		LOG.info("response received : {}",response);
		
		empService.updatEmployee(response);	
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
