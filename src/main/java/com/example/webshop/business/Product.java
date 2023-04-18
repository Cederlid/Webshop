package com.example.webshop.business;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Product(){

    }
    public Product(Category category, String name, double price){
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public Product(String productName, Double productPrice, String productCategory) {
        name = productName;
        price = productPrice;
        category = Category.valueOf(productCategory);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
