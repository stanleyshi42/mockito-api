package com.example.mockito_api.service;

import com.example.mockito_api.entity.Product;
import com.example.mockito_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return repo.findById(id);
    }

    public Product updateProduct(Product product) {
        return repo.save(product);
    }

    public void deleteProductById(int id) {
        repo.deleteById(id);
    }

}
