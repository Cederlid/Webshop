package com.example.webshop.business;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<OrderLine> orderLines;
    @ManyToOne
    Customer customer;

    private boolean isShipped;

    public CustomerOrder() {

    }

    public CustomerOrder(List<OrderLine> orderLines, Customer customer) {
        this.orderLines = orderLines;
        this.customer = customer;
        this.isShipped = false;
    }

    public double getSum() {
        Double temp = 0.0;
        for (OrderLine orderLine : orderLines) {
            temp += (int) (orderLine.product.getPrice() * orderLine.getAmount());
        }
        return temp;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isShipped() {
        return isShipped;
    }

    public void setShipped(boolean shipped) {
        isShipped = shipped;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "" + orderLines;
    }

}
