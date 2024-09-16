package com.springwebfluxmongo.product.config;

import com.springwebfluxmongo.product.entity.Product;
import com.springwebfluxmongo.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Startup implements CommandLineRunner {
    @Autowired
    private final ProductService productService;
    @Override
    public void run(String... args) throws Exception {
        productService.insert(new Product(null,"Laptop",70000,"PCs"));
        productService.insert(new Product(null,"iphone 16",150000,"Phones"));
        productService.insert(new Product(null,"Rings",150,"Accessories"));
    }
}
