package com.example.mockito_api.service;

import com.example.mockito_api.entity.Product;
import com.example.mockito_api.repository.ProductRepository;
import com.example.mockito_api.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    ProductRepository repo;

    @Autowired
    ProductService service;

    @Test
    void testAddProduct() {

        // Expected values
        String expectedName = "Laptop";
        double expectedPrice = 599.99;
        Product expectedProduct = new Product(0, expectedName, expectedPrice);

        when(repo.save(expectedProduct)).thenReturn(expectedProduct);

        // Execute method and perform assertions
        Product actualProduct = service.addProduct(expectedProduct);
        assertEquals(expectedProduct, actualProduct);

        verify(repo, times(1)).save(any(Product.class));
    }

    @Test
    void testGetAllProducts() {

        ArrayList<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());

        when(repo.findAll()).thenReturn(expectedProducts);

        ArrayList<Product> actualProducts = (ArrayList<Product>) service.getAllProducts();
        assertEquals(3, actualProducts.size());

        verify(repo).findAll();
    }
}
