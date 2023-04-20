package com.example.webshop.business;

import jakarta.persistence.*;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    Product product;
    private int amount;

    public OrderLine() {
    }

    public OrderLine(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public OrderLine(Long id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void removeFromAmount(){
        this.amount--;
    }

    @Override
    public String toString() {
        return product + ", Antal: " + amount ;
    }

}
