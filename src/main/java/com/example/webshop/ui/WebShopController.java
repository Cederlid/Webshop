package com.example.webshop.ui;

import com.example.webshop.business.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        m.addAttribute("newCustomer", webShopService.register(name));
        return "showcategories";
    }

    @PostMapping("/addnewproduct")
    public String adminPage(@RequestParam String name, Model m) {
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
        m.addAttribute("products", webShopService.getProductByCategory(category));
        return "showproducts";
    }

    @PostMapping("/searchproducts")
    public String showProducts(@RequestParam String product, Model m) {
        m.addAttribute("products", webShopService.getProductByName(product));
        return "showproducts";
    }

    @GetMapping("all")
    public String showAll(Model m) {
        m.addAttribute("products", webShopService.getProductRepo().findAll());
        return "allproducts";
    }

    @PostMapping("addtocart")
    public String addProductToCart(@RequestParam Long id, @RequestParam int amount, Model m) {
        webShopService.addProductToCart(id, amount);
        return "showcategories";
    }

    @GetMapping("cart")
    public String showCart(Model m) {
        m.addAttribute("items", webShopService.getShoppingCart().getOrderLines());
        m.addAttribute("totalprice",webShopService.getSumOfShoppingCart());
        return "shoppingcart";
    }

    @PostMapping("removefromcart")
    public String removeFromCart(@RequestParam int itemindex, @RequestParam int amount, Model m) {
        webShopService.getShoppingCart().adjustProductInShoppingCart(itemindex, amount);
        m.addAttribute("items", webShopService.getShoppingCart().getOrderLines());
        return "shoppingcart";
    }

    @GetMapping("webshop")
    public String showWebShop(Model m) {
        m.addAttribute("products", webShopService.getProductByCategory(Category.BLÄCKSVAMPAR));
        return "showcategories";
    }

    @PostMapping("completeorder")
    public String checkOutOrder(Model m) {
        webShopService.addOrder();
        m.addAttribute("customerorder", webShopService.getOrder());
        m.addAttribute("totalprice",webShopService.getOrder().getSum());
        return "orderscheckout";
    }

    @GetMapping("allorders")
    public String showAllOrders(Model m){
        m.addAttribute("customerorder", webShopService.getOrderRepository().findAll());
        return "shiporders";
    }
    @PostMapping("shiporder")
    public String shipOrderIfNotShipped(@RequestParam Long id, Model m) {
        webShopService.setShipped(id);
        m.addAttribute("customerorder", webShopService.getOrderRepository().findAll());
        return "shiporders";
    }

}
