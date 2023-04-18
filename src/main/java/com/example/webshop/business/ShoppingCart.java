package com.example.webshop.business;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
List<OrderLine> orderLines;

public ShoppingCart(){
    this.orderLines = new ArrayList<>();
}

public List<OrderLine> getOrderLines(){
    return orderLines;
}

public List<OrderLine> adjustProductInShoppingCart(int index, int amount){
    if (amount == 0){
        getOrderLines().remove(index);
    } else {
        getOrderLines().get(index).setAmount(amount);
    }
    return getOrderLines();
}

public Double productSum(){
    Double temp = 0.0;
    for (OrderLine orderLine: orderLines) {
        temp += (int) (orderLine.product.getPrice() * orderLine.getAmount());
    }
     return temp;
}


}
