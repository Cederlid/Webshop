package com.example.webshop.data;

import com.example.webshop.business.Category;
import com.example.webshop.business.Customer;
import com.example.webshop.business.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByCategory(Category category);
}
