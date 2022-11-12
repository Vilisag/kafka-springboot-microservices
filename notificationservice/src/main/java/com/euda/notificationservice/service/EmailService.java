package com.euda.notificationservice.service;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.euda.notificationservice.model.Message;

public interface EmailService {
    Boolean sendEmail(Message message);
}

@Service
class EmailServiceImpl implements EmailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    @Async
    public Boolean sendEmail(Message message) {
        try {
            logger.info("START... Sending email");

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariable("name", message.getToName());
            context.setVariable("content", message.getContent());
            String html = templateEngine.process("welcome-email", context);

            helper.setTo(message.getTo());
            helper.setText(html, true);
            helper.setSubject(message.getSubject());
            helper.setFrom(message.getFrom() != null ? message.getFrom() : from);
            javaMailSender.send(mimeMessage);

            logger.info("END... Email sent success.");
            return true;
        } catch (Exception e) {
            logger.error("Email sent with error: " + e.getMessage());
            return false;
        }
    }
}