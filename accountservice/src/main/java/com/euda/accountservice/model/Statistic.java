package com.euda.accountservice.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "statistics")
public class Statistic {

    @Id
    private String id;

    @Field(value = "message")
    private String message;

    @CreatedDate
    @Field(value = "created_at")
    private Date createdAt;

    @Field(value = "status")
    private Boolean status;

    public Statistic(String message, Date createdAt) {
        this.message = message;
        this.createdAt = createdAt;
    }
}
