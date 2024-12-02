package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController
{

    @Autowired
    private ProductService service;


    @GetMapping("/showProducts")
    public String productList(Model model)
    {
        List<Product> products = service.fetchAllProduct();
        model.addAttribute("products", products);
        return "productList";
    }


    @GetMapping("/product/{id}")
    public String showProductDetails(@PathVariable int id, Model model)
    {
        Product product = service.fetchProduct(id);
        model.addAttribute("product", product);
        return "productDetails";
    }


    @PostMapping("/updateStock/{id}")
    public String updateProductStock(@PathVariable int id, @RequestParam int stock, Model model)
    {
        Product product = service.fetchProduct(id);
        product.setStock(stock);
        service.updateProduct(product);
        model.addAttribute("product", product);
        return "productDetails";
    }




    @PostMapping("/addNewProduct")
    @ResponseBody
    public Product saveProduct(@RequestBody Product product)
    {
        return service.saveProduct(product);
    }


    @GetMapping("/fetchAllProduct")
    @ResponseBody
    public List<Product> fetchAllProduct()
    {
        return service.fetchAllProduct();
    }


    @GetMapping("/fetchProduct")
    @ResponseBody
    public Product fetchProduct(@RequestParam int id)
    {
        return service.fetchProduct(id);
    }


    @PutMapping("/updateProduct")
    @ResponseBody
    public Product updateProduct(@RequestBody Product product)
    {
        return service.updateProduct(product);
    }


    @DeleteMapping("/deleteProduct")
    @ResponseBody
    public void deleteProduct(@RequestParam int id)
    {
        service.deleteProduct(id);
    }


    @GetMapping("/stockAvailableProductGreaterThan")
    @ResponseBody
    public List<Product> getCurrentStockProduct()
    {
        return service.getCurrentStockProduct();
    }


    @GetMapping("/stockAvailableProductLessThan")
    @ResponseBody
    public List<Product> findByCurrentStock()
    {
        return service.findByCurrentStock();
    }

}
