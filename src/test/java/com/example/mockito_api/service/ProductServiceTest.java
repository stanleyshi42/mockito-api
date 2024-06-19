package com.example.mockito_api.service;

import com.example.mockito_api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    ProductRepository repo;

    @Autowired
    ProductService service;

    @Test
    void test(){

    }
}
