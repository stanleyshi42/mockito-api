package com.example.mockito_api.controller;

import com.example.mockito_api.entity.Product;
import com.example.mockito_api.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @MockBean
    ProductService service;

    @Autowired
    ProductController controller;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testAddProduct() {

        // Expected values
        String expectedName = "Laptop";
        double expectedPrice = 599.99;
        Product expectedProduct = new Product(0, expectedName, expectedPrice);

        when(service.addProduct(any(Product.class))).thenReturn(expectedProduct);

        // Execute method and perform assertions
        Product actualProduct = controller.addProduct(expectedProduct);
        assertEquals(expectedProduct, actualProduct);
        assertEquals(expectedName, actualProduct.getName());
        assertEquals(expectedPrice, actualProduct.getPrice());

        verify(service, times(1)).addProduct(any(Product.class));
    }

    @Test
    void testGetAllProducts() throws Exception {

        // Expected values
        ArrayList<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());

        when(service.getAllProducts()).thenReturn(expectedProducts);

        // Execute request and perform assertions
        MvcResult result = mockMvc.perform(get("/product")).andReturn();

        assertEquals(200, result.getResponse().getStatus());
        verify(service).getAllProducts();
    }

    @Test
    void testGetProductById() {

        // Expected values
        int expectedId = 1;
        String expectedName = "Laptop";
        double expectedPrice = 599.99;
        Product expectedProduct = new Product(expectedId, expectedName, expectedPrice);

        when(service.getProductById(any(Integer.class))).thenReturn(Optional.of(expectedProduct));

        // Execute method and perform assertions
        ResponseEntity<Object> response = controller.getProductById(expectedId);
        Product acutalProduct = (Product) response.getBody();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedId, acutalProduct.getId());
        assertEquals(expectedName, acutalProduct.getName());
        assertEquals(expectedPrice, acutalProduct.getPrice());

        verify(service, times(1)).getProductById(any(Integer.class));
    }

    @Test
        // Deleting an ID that doesn't exist
    void testGetProductWithInvalidId() {

        when(service.getProductById(any(Integer.class))).thenReturn(Optional.empty());

        ResponseEntity<Object> response = controller.getProductById(1);
        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());

        verify(service, times(1)).getProductById(any(Integer.class));
    }

    @Test
    void testUpdateProduct() {

        // Expected values
        String expectedName = "Laptop";
        double expectedPrice = 599.99;
        Product expectedProduct = new Product(0, expectedName, expectedPrice);

        when(service.updateProduct(any(Product.class))).thenReturn(expectedProduct);

        // Execute method and perform assertions
        ResponseEntity<Object> response = controller.updateProduct(expectedProduct);
        assertEquals(201, response.getStatusCode().value());

        verify(service, times(1)).updateProduct(any(Product.class));
    }

    @Test
    void testDeleteProductById() {

        int expectedId = 1;
        String expectedName = "Laptop";
        double expectedPrice = 599.99;
        Product expectedProduct = new Product(expectedId, expectedName, expectedPrice);

        when(service.getProductById(expectedId)).thenReturn(Optional.of(expectedProduct));

        ResponseEntity<Object> response = controller.deleteProductById(expectedId);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Product deleted", response.getBody());

        verify(service).getProductById(expectedId);
    }

    @Test
        // Deleting an ID that doesn't exist
    void testDeleteProductWithInvalidId() {

        when(service.getProductById(any(Integer.class))).thenReturn(Optional.empty());

        ResponseEntity<Object> response = controller.deleteProductById(1);
        assertEquals(404, response.getStatusCode().value());

        verify(service).getProductById(any(Integer.class));
    }

}
