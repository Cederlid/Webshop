package com.example.webshop.ui;

import com.example.webshop.business.Category;
import com.example.webshop.business.Customer;
import com.example.webshop.business.Product;
import com.example.webshop.business.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebShopController {
    @Autowired
    private WebShopService webShopService;

    @PostMapping("/login")
    public String login(@RequestParam String name, Model m) {
        Customer customer = webShopService.login(name);
        if (customer == null) {
            return "errormessage";
        } else {
            m.addAttribute("customername", webShopService.getCustomer().getName());
            return "showcategories";
        }
    }

    @PostMapping("/register")
    public String registerNewCustomer(@RequestParam String name, Model m) {
        webShopService.register(name);
        m.addAttribute("newCustomer", webShopService.register(name));
        return "showcategories";
    }

    @PostMapping("/addnewproduct")
    public String adminpage(@RequestParam String name, Model m) {
        webShopService.adminLogin(name);
        m.addAttribute("name", webShopService.getAdmin().getName());
        m.addAttribute("product", new Product());
        return "newproduct";
    }

    @PostMapping("/new")
    public String addNewProduct(Product product, Model m) {
        webShopService.add(product);
        m.addAttribute("product", new Product());
        return "newproduct";
    }

    @PostMapping("/webshop")
    public String showCategories(@RequestParam Category category, Model m) {
        m.addAttribute("products",webShopService.getProductByCategory(category));
        return "showproducts";
    }

    @PostMapping("/searchproducts")
    public String showProducts(@RequestParam String product, Model m){
        m.addAttribute("products", webShopService.getProductByName(product));
        return "showproducts";
    }


}
