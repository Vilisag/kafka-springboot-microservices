package com.euda.notificationservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Message {
    private String from;
    private String to;
    private String toName;
    private String subject;
    private String content;
}
