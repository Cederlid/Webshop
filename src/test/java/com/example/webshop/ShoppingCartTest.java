package com.example.webshop;

import com.example.webshop.business.Category;
import com.example.webshop.business.OrderLine;
import com.example.webshop.business.Product;
import com.example.webshop.business.ShoppingCart;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    ShoppingCart shoppingCart;
    Product product1;
    Product product2;

    @BeforeEach
    public void initialize() {
        shoppingCart = new ShoppingCart();
        product1 = new Product(Category.BLÄCKSVAMPAR, "RutBläcksvamp", 66);
        product2 = new Product(Category.ÖVRIGA, "Toppslätskivling", 200);
    }

    @Test
    public void testSumOfShoppingCart() {
        shoppingCart.addProductToShoppingCart(product1, 1);
        shoppingCart.addProductToShoppingCart(product2, 1);
        assertEquals(266, shoppingCart.sumOfShoppingCart());
    }

    @Test
    public void testAdjustProductInShoppingCart() {
        shoppingCart.addProductToShoppingCart(product1, 2);
        List<OrderLine> result = shoppingCart.adjustProductInShoppingCart(0, 1);
        assertEquals(1, result.get(0).getAmount());

    }

    @Test
    public void testClearShoppingCart() {
        shoppingCart.addProductToShoppingCart(product2, 2);
        shoppingCart.clearShoppingCart();
        assertEquals(0, shoppingCart.getOrderLines().size());
    }

    @Test
    public void testAddProductToShoppingCart() {
        shoppingCart.addProductToShoppingCart(product1, 1);
        shoppingCart.addProductToShoppingCart(product2, 2);
        assertEquals(2, shoppingCart.getOrderLines().size());
    }

}
