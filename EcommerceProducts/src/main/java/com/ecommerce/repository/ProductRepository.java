package com.ecommerce.repository;

import com.ecommerce.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer>
{

    List<Product> findByStockGreaterThan(int stock);

    List<Product> findByStockLessThan(int stock);

}