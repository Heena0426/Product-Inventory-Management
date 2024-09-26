package com.Product.Inventory.product_inventory.service;

import com.Product.Inventory.product_inventory.model.Product;
import com.Product.Inventory.product_inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class ProductService {

    @Value("${file.upload-dir}")
    String uploadDir;
    private ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }

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

    public Product addProduct(String name, String category , int price , int stock,MultipartFile photo) throws Exception {
        Product product=new Product();
      product.setName(name);
      product.setCategory(category);
      product.setPrice(price);
      product.setStock(stock);


        String fileName= UUID.randomUUID()+"_"+ photo.getOriginalFilename();
        Path filePath= Paths.get(uploadDir+fileName);
        photo.transferTo(filePath);
        product.setImageUrl(filePath.toString());
        return productRepository.save(product);
    }

    public void importProductsFromCsv(MultipartFile file) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");


                if (data[0].equals("ID")) continue;


                Product product = new Product();
                product.setName(data[1]);
                product.setCategory(data[2]);
                product.setPrice(Integer.parseInt(data[3]));
                product.setStock(Integer.parseInt(data[4]));
                product.setImageUrl(data[5]);
                System.out.println(product);

                productRepository.save(product);
            }
        }
    }
    public void exportProductsToCsv(String filePath) throws IOException {
        List<Product> products = productRepository.findAll();
        try (FileWriter writer = new FileWriter(filePath)) {

writer.write("ID,Name,Category,Price,Stock,ImageUrl\n");

            for (Product product : products) {
                writer.write(product.getId() + "," + product.getName() + "," +
                        product.getCategory() + "," + product.getPrice() + "," +
                        product.getStock() + "," + product.getImageUrl() + "\n");
            }


        }
    }
}


