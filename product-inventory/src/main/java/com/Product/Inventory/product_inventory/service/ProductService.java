package com.Product.Inventory.product_inventory.service;

import com.Product.Inventory.product_inventory.model.Product;
import com.Product.Inventory.product_inventory.repository.ProductRepository;
import org.apache.tomcat.jni.Buffer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class ProductService {

    @Value("${file.upload-dir}")
    String uploadPhoto;
    private ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }
//    public String addProduct(String name, String category, Double price, int stock, MultipartFile file){
//        Product product=new Product();
//        product.setName(name);
//        product.setCategory(category);
//        product.setPrice(price);
//        product.getStock();
//        product.getImageUrl();


    public List<Product> filterProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> filterProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> filterProductsByStock(int stock) {
        return productRepository.findByStockGreaterThan(stock);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void importProductsToCsv(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            Product product = new Product();
            product.setName(data[1]);
            product.setCategory(data[2]);
            product.setPrice(new BigDecimal(data[3]));
            product.setStock(Integer.parseInt(data[4]));
            product.setImageUrl(data[5]);
            productRepository.save(product);
        }
    }

    public String uploadProductImage(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get("uploads", filename);
        Files.write(filePath, file.getBytes());
        return productRepository.toString();

    }


    public void exportProductToCsv(String filePath) throws IOException {
        List<Product> products = productRepository.findAll();
        FileWriter writer = new FileWriter(filePath);
        writer.append("ID, Name, Category,Price, Stock, ImageUrl");
        for (Product product : products) {
            writer.append(product.getId() + "," + product.getName() + "," +
                    product.getCategory() + "," + product.getPrice() + "," + product.getStock() + "," + product.getImageUrl() + "\n");

        }
        writer.close();

    }

}


