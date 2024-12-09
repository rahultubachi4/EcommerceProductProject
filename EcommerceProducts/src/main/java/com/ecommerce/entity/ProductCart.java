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

@Document(collection = "Cart")

public class ProductCart
{
    @Id
    private int cartId;

    private int productId;
    private String productName;
    private String productCode;
    private String productStock;
    private String productDetails;
    private String productImages;
    private int quantity;
    private int price;
}
