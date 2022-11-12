package com.euda.accountservice.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "accounts")
public class Account {

    @Id
    private String id;

    @Field(value = "email")
    private String email;

    @Field(value = "password")
    private String password;

    @Field(value = "name")
    private String name;

    @Field(value = "roles")
    private Set<String> roles;
}
