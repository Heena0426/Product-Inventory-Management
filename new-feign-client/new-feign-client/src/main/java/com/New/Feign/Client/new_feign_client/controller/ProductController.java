package com.New.Feign.Client.new_feign_client.controller;

import com.New.Feign.Client.new_feign_client.model.Product;
import com.New.Feign.Client.new_feign_client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Integer id){
        Product product = productService.getproductById(id);
        return product;
    }
}
