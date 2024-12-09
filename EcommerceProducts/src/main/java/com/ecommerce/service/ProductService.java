package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{

    @Autowired
    private ProductRepository repo;



    public Product saveProduct(Product product)
    {
        return repo.save(product);
    }


    public List<Product> fetchAllProduct()
    {
        return repo.findAll();
    }


    public Product fetchProduct(int id)
    {
        Optional<Product> product = repo.findById(id);

        if (product.isEmpty())
        {
            throw new RuntimeException("Product not found");
        }
        return product.get();
    }


    public Product updateProduct(Product product)
    {
        Optional<Product> productUpdate = repo.findById(product.getId());

        if (productUpdate.isEmpty())
        {
            throw new RuntimeException("Product not updated");
        }

        Product existingProduct = productUpdate.get();
        existingProduct.setStock(product.getStock());
        existingProduct.setDetails(product.getDetails());
        existingProduct.setImages(product.getImages());
        return repo.save(existingProduct);
    }


    public void deleteProduct(int id)
    {
        Optional<Product> productUpdate = repo.findById(id);

        if (productUpdate.isEmpty()) {
            throw new RuntimeException("Product not deleted ");
        }
        repo.delete(productUpdate.get());
    }


    public List<Product> getCurrentStockProduct()
    {
        return repo.findByStockGreaterThan(0);
    }


    public List<Product> findByCurrentStock()
    {
        return repo.findByStockLessThan(10);
    }


}
