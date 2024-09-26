package com.Product.Inventory.product_inventory.controller;

import com.Product.Inventory.product_inventory.model.Product;
import com.Product.Inventory.product_inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

//    @PostMapping("/import")
//    public ResponseEntity<String> importProductToCsv(String filePath) throws IOException{
//        productService.importProductsToCsv(filePath);
//        return ResponseEntity.ok("Products imported successfully");
//    }
//    @GetMapping("/export")
//    public ResponseEntity<String> exportProductToCsv(String filePath) throws IOException{
//        productService.exportProductToCsv("product.csv");
//        return ResponseEntity.ok("Product exported successfully");
//    }
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category){
        return productService.filterProductsByCategory(category);
    }
    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestParam BigDecimal min, @RequestParam BigDecimal max){
        return productService.filterProductsByPriceRange(min, max);

    }
    @GetMapping("/stock/{stock}")
    public List<Product> getProuctsByStock(@PathVariable int stock){
        return productService.filterProductsByStock(stock);
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadProductImage(@RequestParam("file")MultipartFile file){
        try{
            productService.uploadProductImage(file);
            return ResponseEntity.ok("Image uploaded");

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Image upload fail");
        }
    }
//    @PostMapping("/import")
//    public ResponseEntity<String> importProducts(@RequestParam ("file") MultipartFile file){
//        productService.importProductsToCsv(filePath);
//        return ResponseEntity.ok("Product imported successfully");
//    }

}