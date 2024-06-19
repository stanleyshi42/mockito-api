package com.example.mockito_api.controller;

import com.example.mockito_api.entity.Product;
import com.example.mockito_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        product.setId(0);   // Ensures that ID is auto generated
        return service.addProduct(product);
    }

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable int id) {
        Optional<Product> result = service.getProductById(id);

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result.get());
    }

    @PutMapping("/product")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        Product result = service.updateProduct(product);
        return ResponseEntity.status(201).body(result);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable int id) {
        Optional<Product> result = service.getProductById(id);

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteProductById(id);
        return ResponseEntity.ok("Product deleted");
    }
}
