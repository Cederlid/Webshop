package com.example.webshop.restUi;

import com.example.webshop.business.Category;
import com.example.webshop.business.Product;
import com.example.webshop.business.WebShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/rest/bycategory/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Category category) {
        if (webShopService.getProductByCategory(category).isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            webShopService.getProductByCategory(category);
            return ResponseEntity.accepted().body(webShopService.getProductByCategory(category));
        }
    }

    @GetMapping("/rest/byname/{name}")
    public List<Product> getProductByName(@PathVariable String name) {
        return webShopService.getProductByName(name);
    }

    @GetMapping("/rest/byid/{id}")
    public ResponseEntity<String> getProductById(@PathVariable Long id) {
        if (webShopService.getProductRepo().findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            webShopService.getProductById(id);
            return ResponseEntity.accepted().body("Här är din " + webShopService.getProductById(id));
        }

    }

    @DeleteMapping("/rest/deleteproduct/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        if (webShopService.getProductRepo().findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            webShopService.deleteProductById(id);
            return ResponseEntity.ok("Produkten är borttagen!");
        }

    }

    @PostMapping("/rest/addproduct")
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product, BindingResult br) {
        if (br.hasErrors()) {
            return ResponseEntity.badRequest().body("Något har blivit fel!");
        } else {
            webShopService.add(product);
            return ResponseEntity.accepted().body("Det gick bra!" + product);
        }

    }

    @PutMapping("/rest/updateprice/{id}/{price}")
    public ResponseEntity<String> updateProductPriceById(@PathVariable Long id, @PathVariable double price) {
        if (price < 1 || price > 1000) {
            return ResponseEntity.badRequest().body("FEL! priset ska ligga mellan 1 och 1000");
        } else {
            webShopService.updateProductPriceById(price, id);
            return ResponseEntity.accepted().body("Det gick utmärkt");
        }
    }
}
