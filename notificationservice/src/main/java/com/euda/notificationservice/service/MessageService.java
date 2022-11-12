package com.euda.notificationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.euda.notificationservice.model.Message;

@Service
public class MessageService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmailService emailService;

    @KafkaListener(id = "notificationGroup", topics = "notification")
    public void listen(Message message) {
        logger.info("Recieved :", message.getTo());
        emailService.sendEmail(message);
    }
}
