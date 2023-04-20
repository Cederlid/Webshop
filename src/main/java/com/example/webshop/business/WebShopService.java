package com.example.webshop.business;

import com.example.webshop.data.AdminRepository;
import com.example.webshop.data.CustomerRepository;
import com.example.webshop.data.OrderRepository;
import com.example.webshop.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class WebShopService {

    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepo;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AdminRepository adminRepo;
    private Admin admin;
    private Product product;
    private ShoppingCart shoppingCart;
    private Customer customer;
    boolean isLoggedIn;

    public WebShopService() {
        shoppingCart = new ShoppingCart();
    }

    public void add(Product product) {
        productRepo.save(product);
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).get();
    }

    public Customer login(String name) {
        Customer c = null;
        List<Customer> customerList = customerRepository.findByName(name);
        if (customerList.size() > 0) {
            c = customerList.get(0);
            isLoggedIn = true;
        }
        customer = c;
        return c;
    }

    public Customer register(String name) {
        Customer c = null;
        List<Customer> customerList = customerRepository.findByName(name);
        System.out.println(customerList + "lista");
        if (customerList.size() == 0) {
            Customer cust = new Customer(name);
            c = customerRepository.save(cust);
            isLoggedIn = true;
        } else {
            System.out.println("Logga in om du är en befintlig medlem!");
        }
        customer = c;
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

    public  ShoppingCart addProductToCart(Long id, int amount){
        shoppingCart.addProductToShoppingCart(getProductById(id),amount);
        System.out.println(id + " " + amount);
        return shoppingCart;
    }

    public void addOrder(){
        CustomerOrder co =new CustomerOrder(getShoppingCart().getOrderLines(),customer);
        customer.addOrder(co);
        customer = customerRepository.save(customer);
        shoppingCart.clearShoppingCart();
        emailService.sendSimpleMessage("hardrock_17@hotmail.com", "Köpta varor","Tack för din beställning! Dina varor är skickade! " + getCustomerOrder());
    }

    public void saveOrderDb(CustomerOrder customerOrder){
        customerOrder = orderRepository.save(customerOrder);
    }

    public Customer getCustomer() {
        return customer;
    }

    public ProductRepository getProductRepo() {
        return productRepo;
    }

    public Admin getAdmin() {
        return admin;
    }

    public List<Product> getProductByCategory(Category category) {
        return productRepo.findByCategory(category);
    }

    public List<Product> getProductByName(String product) {
        return productRepo.findByName(product);
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public CustomerOrder getOrder() {
       return customer.getOrders().get(customer.getOrders().size()-1);
    }

    public void setShipped(Long id){
        CustomerOrder co = orderRepository.findById(id).get();
        co.setShipped(true);
        orderRepository.save(co);
    }

    public double getSumOfShoppingCart(){
        double sum = shoppingCart.sumOfShoppingCart();
        return sum;
    }
    public List<CustomerOrder>getCustomerOrder() {
        return customer.getOrders();
    }

    public List<CustomerOrder>getAllOrders(){
        return orderRepository.findAll();
    }

}
