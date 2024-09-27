package com.Product.Inventory.product_inventory.controller;

import com.Product.Inventory.product_inventory.exception.StockNotFoundException;
import com.Product.Inventory.product_inventory.model.Product;
import com.Product.Inventory.product_inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.filterProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestParam Integer min, @RequestParam Integer max) {
        return productService.filterProductsByPriceRange(min, max);
    }

    @GetMapping("/stock/{stock}")
    public List<Product> getProductsByStock(@PathVariable int stock) {
        return productService.filterProductsByStock(stock);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProductImage(
            @RequestParam("file") MultipartFile file,
//            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("price")int price,
            @RequestParam("stock") int stock) {
        {
            try {
                productService.addProduct( name, category, price, stock,file);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok("Image uploaded");

        }

    }
    @PostMapping("/import")
    public ResponseEntity<String> importProductsFromCsv(@RequestParam("file") MultipartFile file) {
        try {
            productService.importProductsFromCsv(file);
            return ResponseEntity.ok("Products imported successfully from CSV file.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while importing products: " + e.getMessage());
        }
    }
    @GetMapping("/export")
    public ResponseEntity<String> exportProductsToCsv(@RequestParam("filePath") String filePath) {
        try {
            productService.exportProductsToCsv(filePath);
            return ResponseEntity.ok("Products exported successfully to CSV file.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while exporting products: " + e.getMessage());
        }
    }
}