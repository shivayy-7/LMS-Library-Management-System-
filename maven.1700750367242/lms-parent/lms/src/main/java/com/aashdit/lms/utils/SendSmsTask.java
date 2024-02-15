package com.aashdit.lms.utils;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aashdit.lms.repository.MessageTemplateRepository;
import com.aashdit.lms.service.SmsService;

@Component
public class SendSmsTask {
	
	@Autowired
	private MessageTemplateRepository messageTemplateRepository;
	
	@Autowired
	private SmsService smsService;
	
//	@Scheduled(fixedRate = 50000)
//	public void sendNotification() {
//		ResourceBundle rb = ResourceBundle.getBundle("messages");
//		String status = rb.getString("NOTIFICATION.MESSAGE");
//		
//	}

}
