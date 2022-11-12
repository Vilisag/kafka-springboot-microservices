package com.euda.accountservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "messages")
public class Message {
    @Id
    private String id;

    @Field(value = "from")
    private String from;

    @Field(value = "to_email")
    private String to;

    @Field(value = "to_name")
    private String toName;

    @Field(value = "subject")
    private String subject;

    @Field(value = "content")
    private String content;

    @Field(value = "status")
    private Boolean status;

    public Message(String to, String toName, String subject, String content) {
        this.to = to;
        this.toName = toName;
        this.subject = subject;
        this.content = content;
    }
}
