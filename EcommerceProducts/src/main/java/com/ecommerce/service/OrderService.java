package com.ecommerce.service;

import com.ecommerce.entity.Order;
import com.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public Order placeOrder(Order order)
    {

        order.getItems().forEach(item -> item.setTotalQuantity(item.getPrice() * item.getQuantity()));


        int totalOrderPrice = order.getItems().stream()
                .mapToInt(item -> item.getTotalQuantity())
                .sum();
        order.setTotalOrderPrice(totalOrderPrice);

        return repository.save(order);
    }

    public List<Order> fetchOrder()
    {
        return repository.findAll();
    }
}
