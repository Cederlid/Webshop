package com.example.webshop.restUi;

import com.example.webshop.business.Category;
import com.example.webshop.business.Product;
import com.example.webshop.business.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestWebShopController {

    @Autowired
    WebShopService webShopService;

    @GetMapping("/rest/allproducts")
    public List<Product> getAllProducts() {
        return webShopService.getAllProducts();
    }

    @GetMapping("/rest/bycategory")
    public List<Product> getProductByCategory(@RequestParam Category category) {
        return webShopService.getProductByCategory(category);
    }

    @GetMapping("/rest/byname/{name}")
    public List<Product> getProductByName(@PathVariable String name) {
        return webShopService.getProductByName(name);
    }

    @GetMapping("/rest/byid/{id}")
    public Product getProductById(@PathVariable Long id) {
        return webShopService.getProductById(id);
    }

    @DeleteMapping("/rest/deleteproduct/{id}")
    public List<Product> deleteProductById(@PathVariable Long id) {
        return webShopService.deleteProductById(id);
    }

    @PostMapping("/rest/addproduct")
    public List<Product> addProduct(@RequestParam String name, @RequestParam double price, @RequestParam Category category) {
        webShopService.add(new Product(category, name, price));
        return webShopService.getAllProducts();
    }

    @PutMapping("/rest/updateprice")
    public List<Product> updateProductPriceById(@RequestParam Long id, @RequestParam double price){
        webShopService.updateProductPriceById(price, id);
        return webShopService.getAllProducts();
    }
}
