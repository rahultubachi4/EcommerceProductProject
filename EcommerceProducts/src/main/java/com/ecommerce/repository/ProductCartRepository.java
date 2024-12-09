package com.ecommerce.repository;

import com.ecommerce.entity.ProductCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartRepository extends MongoRepository<ProductCart, Integer>
{

}
