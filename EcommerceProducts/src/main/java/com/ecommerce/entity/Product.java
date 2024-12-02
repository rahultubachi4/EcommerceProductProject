package com.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "Product")
public class Product
{
    @Id
    private int id;

    private String name;

    private String code;

    private int stock;

    private String details;

    private String images;

}
