package com.example.webshop.business;

import com.example.webshop.data.AdminRepository;
import com.example.webshop.data.CustomerRepository;
import com.example.webshop.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class WebShopService {
    @Autowired
    CustomerRepository costumerRepo;
    @Autowired
    ProductRepository productRepo;
    @Autowired
    AdminRepository adminRepo;
    private Admin admin;
    private Customer customer;
    boolean isLoggedIn;

    public List<Product> listAll() {
        return productRepo.findAll();
    }

    public void save(Product product) {
        productRepo.save(product);
    }

    public Customer login(String name) {
        Customer c = null;
        List<Customer> customerList = costumerRepo.findByName(name);
        if (customerList.size() > 0) {
            c = customerList.get(0);
        }
        customer = c;
        isLoggedIn = true;
        return c;
    }

    public Customer register(String name) {
        Customer c = null;
        List<Customer> customerList = costumerRepo.findByName(name);
        if (customerList.size() == 0) {
            Customer cust = new Customer(name);
            c = costumerRepo.save(cust);
        }
        else {
            System.out.println("Logga in om du är en befintlig medlem!");
        }
        customer =c;
        isLoggedIn = true;
        return c;
    }

    public Admin adminLogin(String name) {
        Admin a;
        List<Admin> adminList = adminRepo.findByName(name);
        if (adminList.size() == 0) {
            Admin adm = new Admin(name);
            a = adminRepo.save(adm);
        } else {
            a = adminList.get(0);
        }
        admin = a;
        isLoggedIn = true;
        return a;
    }

    public Product get(long id) {
        return productRepo.findById(id).get();
    }

    public void delete(long id) {
        productRepo.deleteById(id);
    }


    public CustomerRepository getCostumerRepo() {
        return costumerRepo;
    }

    public void setCostumerRepo(CustomerRepository costumerRepo) {
        this.costumerRepo = costumerRepo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public ProductRepository getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public AdminRepository getAdminRepo() {
        return adminRepo;
    }

    public void setAdminRepo(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


}
