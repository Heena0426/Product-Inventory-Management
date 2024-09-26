package com.New.Feign.Client.new_feign_client.service;

import com.New.Feign.Client.new_feign_client.interfaces.PricingClient;
import com.New.Feign.Client.new_feign_client.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private PricingClient pricingClient;

    public Product getproductById(Integer id){
       return pricingClient.getProducts(id);
    }
}
