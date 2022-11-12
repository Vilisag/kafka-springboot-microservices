package com.euda.imageservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "images")
public class Image {
    @Id
    private String id;

    @Field(value = "title")
    private String title;

    @Field(value = "url")
    private String url;
}
