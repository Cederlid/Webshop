package com.example.webshop.ui;

import com.example.webshop.business.Customer;
import com.example.webshop.business.Product;
import com.example.webshop.business.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebShopController {
    @Autowired
    private WebShopService webShopService;

    @PostMapping("/login")
    public String login(@RequestParam String name, Model m) {
        Customer customer = webShopService.login(name);
        if (customer == null){
            return "errormessage";
        }else {
            m.addAttribute("customername", webShopService.getCustomer().getName());
            return "showproducts";
        }
    }
    @PostMapping("/register")
    public String registerNewCostumer(@RequestParam String name, Model m){
        webShopService.register(name);
        m.addAttribute("newCostumer",webShopService.register(name));
        return "showproducts";
    }


    @GetMapping("/webshop")
    public String
    shopPage(Model m) {
        List<Product> listProducts = webShopService.listAll();
        m.addAttribute("listProducts", listProducts);
        return "showproducts";
    }

    @PostMapping("/adminlogin")
    public String adminLogin(@RequestParam String name, Model m) {
        webShopService.adminLogin(name);
        m.addAttribute("adminname", webShopService.getAdmin().getName());
        return "adminpage";
    }

//    @PostMapping("/adminwebshop")
//    public String createProduct(Model m){
//        m.addAttribute("product", webShopService.save());
//        return "adminwebshop";
//    }


}
