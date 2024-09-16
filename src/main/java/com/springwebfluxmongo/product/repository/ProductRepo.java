package com.springwebfluxmongo.product.repository;

import com.springwebfluxmongo.product.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepo extends ReactiveMongoRepository<Product,Long> {

    Flux<Product>findByName(String name);

}
