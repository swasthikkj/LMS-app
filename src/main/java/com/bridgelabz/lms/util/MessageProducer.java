package com.bridgelabz.lms.util;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.lms.config.MessagingConfig;

@Component
public class MessageProducer {
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public void sendMessage(Email email) {
		rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, email);
		System.out.println("Is producer returned ::: " + rabbitTemplate.isReturnListener());
	}
}
