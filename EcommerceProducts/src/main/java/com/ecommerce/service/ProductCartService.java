package com.ecommerce.service;

import com.ecommerce.entity.ProductCart;
import com.ecommerce.repository.ProductCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCartService
{

    @Autowired
    private ProductCartRepository cartRepo;

    public ProductCart addToCart(ProductCart cart)
    {
        return cartRepo.save(cart);
    }



    public List<ProductCart> fetchCartItems()
    {
        return cartRepo.findAll();
    }


    public void removeCartItem(int cartId)
    {
        cartRepo.deleteById(cartId);
    }


    public void clearCart()
    {
        cartRepo.deleteAll();
    }




}
