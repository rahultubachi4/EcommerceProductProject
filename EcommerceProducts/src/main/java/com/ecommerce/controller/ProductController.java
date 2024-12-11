package com.ecommerce.controller;

import com.ecommerce.entity.Item;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.ProductCart;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductCartService;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController
{

    @Autowired
    private ProductService service;


    @Autowired
    private ProductCartService cartService;

    @Autowired
    private OrderService orderService;




    @GetMapping("/cart")
    public String viewCart(Model model)
    {
        List<ProductCart> cartItems = cartService.fetchCartItems();
        model.addAttribute("cartItems", cartItems);
        return "cartList";
    }


    @PostMapping("/addToCart")
    @ResponseBody
    public String addToCart(@RequestParam int productId, @RequestParam String productName,
                            @RequestParam int quantity, @RequestParam int price,
                            @RequestParam String productCode, @RequestParam String productStock,
                            @RequestParam String productDetails, @RequestParam String productImages)
    {


        Product product = service.fetchProduct(productId);


        if (quantity <= product.getStock())
        {

            ProductCart cart = new ProductCart();
            cart.setCartId((int) (Math.random() * 10000));
            cart.setProductId(productId);
            cart.setProductName(productName);
            cart.setProductCode(productCode);
            cart.setProductStock(productStock);
            cart.setProductDetails(productDetails);
            cart.setProductImages(productImages);
            cart.setQuantity(quantity);
            cart.setPrice(price);

            cartService.addToCart(cart);

            return "Product added to cart successfully...!!!!";

        } else {
            return "Not enough stock available! Only " + product.getStock() + " items are available.";
        }
    }


    @GetMapping("/fetchAllCartItems")
    @ResponseBody
    public List<ProductCart> fetchCartItems()
    {
        return cartService.fetchCartItems();
    }



    @GetMapping("/checkout")
    public String showPaymentForm(Model model)
    {
        List<ProductCart> cartItems = cartService.fetchCartItems();
        int totalOrderPrice = cartItems.stream().mapToInt(item -> item.getPrice() * item.getQuantity()).sum();
        model.addAttribute("totalOrderPrice", totalOrderPrice);
        return "checkout";
    }



    @PostMapping("/processPayment")
    public String processPayment(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("paymentMethod") String paymentMethod,
            @RequestParam("paymentStatus") String paymentStatus,
            @RequestParam("paymentTime") String paymentTime,
            @RequestParam("customerName") String customerName,
            @RequestParam("customerAddress") String customerAddress,
            @RequestParam("customerZipCode") String customerZipCode,
            Model model
    )
    {

        List<ProductCart> cartItems = cartService.fetchCartItems();


        List<Item> items = cartItems.stream().map(cartItem -> {
            Item item = new Item();
            item.setItemId("ITEM" + (int) (Math.random() * 10000));
            item.setProductName(cartItem.getProductName());
            item.setProductCode(cartItem.getProductCode());
            item.setPrice(cartItem.getPrice());
            item.setQuantity(cartItem.getQuantity());
            item.setTotalQuantity(cartItem.getPrice() * cartItem.getQuantity());
            return item;
        }).collect(Collectors.toList());


        Order order = new Order();
        order.setOrderId("ORD" + (int) (Math.random() * 10000));
        order.setCustomerName(customerName);
        order.setCustomerAddress(customerAddress);
        order.setCustomerZipCode(customerZipCode);
        order.setItems(items);
        orderService.placeOrder(order);


        model.addAttribute("paymentId", paymentId);
        model.addAttribute("paymentMethod", paymentMethod);
        model.addAttribute("paymentStatus", paymentStatus);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("order", order);

        return "paymentSuccess";
    }




    @GetMapping("/fetchAllOrders")
    @ResponseBody
    public List<Order> fetchOrder()
    {
        return orderService.fetchOrder();
    }






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

        if (product == null)
        {
            throw new RuntimeException("Product not found with id: " + id);
        }

        model.addAttribute("product", product);

        if (product.getStock() == 0){
            model.addAttribute(true);
        } else {
            model.addAttribute(false);
        }

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
