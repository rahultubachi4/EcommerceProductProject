package com.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")

public class Order
{
    @Id
    private String orderId;

    private String customerName;

    private String customerAddress;

    private String customerZipCode;

    private int totalOrderPrice;


    private List<Item> items;
}
