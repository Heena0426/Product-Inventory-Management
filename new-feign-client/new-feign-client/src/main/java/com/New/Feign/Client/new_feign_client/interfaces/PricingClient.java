package com.New.Feign.Client.new_feign_client.interfaces;

import com.New.Feign.Client.new_feign_client.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="pricingClient",url = "https://freetestapi.com/api/v1/products")
public interface PricingClient {
    @GetMapping("/{productId}")
    Product getProducts(@PathVariable("productId") Integer productId);
}
