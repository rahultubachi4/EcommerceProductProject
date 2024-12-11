package com.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item
{
    @Id
    private String itemId;
    private String productName;
    private String productCode;
    private int price;
    private int quantity;
    private int totalQuantity;
}
