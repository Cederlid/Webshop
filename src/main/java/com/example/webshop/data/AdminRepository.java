package com.example.webshop.data;

import com.example.webshop.business.Admin;
import com.example.webshop.business.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findByName(String name);
}
