package com.euda.notificationservice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euda.notificationservice.model.Message;
import com.euda.notificationservice.service.EmailService;

@RestController
@RequestMapping("/api/v1")
public class NotificationController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/notifications/email")
    public Map<String, String> email(@Valid @RequestBody Message message) {
        Boolean result = emailService.sendEmail(message);
        Map<String, String> response = new HashMap<>();
        response.put("code", result ? "200" : "500");
        response.put("message", result ? "Email sent success." : "Sending error.");
        return response;
    }
}
