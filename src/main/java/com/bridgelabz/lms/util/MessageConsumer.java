package com.bridgelabz.lms.util;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.bridgelabz.lms.config.MessagingConfig;

@Component
public class MessageConsumer {
	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(Email email) {
		System.out.println("message received from queue:" + email);
	}
}
