package com.springwebfluxmongo.product.controller;

import com.springwebfluxmongo.product.entity.Product;
import com.springwebfluxmongo.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable String id){
        return productService.findById(id);
    }

    @GetMapping(value = "",produces = {"text/event-stream"})
    public Flux<Product>findAll(){
        return productService.findAll();
    }

    @GetMapping("/name/{name}")
    public Flux<Product>findByName(@PathVariable String name){
        return productService.findByName(name);
    }

    @PostMapping()
    public Mono<Product> insert(@RequestBody Product product){
        return productService.insert(product);
    }

    @PutMapping()
    public Mono<Product> update(@RequestBody Product product){
        return productService.update(product);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return productService.delete(id);
    }
    @GetMapping("/cnt")
    public Integer getProductCnt(){
        return 20;
    }

    @GetMapping(value = "/react-cnt",produces = {"text/event-stream"})
    public Mono<Integer> getProductCntReactive(){
        return Mono.just(20);
    }

    @GetMapping("/all")
    public List<Integer>getAll() throws InterruptedException {
        List<Integer>products=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            products.add(i+1);
            Thread.sleep(1000);
        }
        return products;
    }

    @GetMapping(value = "/react-all",produces = {"text/event-stream"})
    public Flux<Integer> getAllReactive() throws InterruptedException {
        return Flux.create(fluxList->{
            for (int i = 0; i <20 ; i++) {
                fluxList.next(i);
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
            fluxList.complete();
        });
    }
}

