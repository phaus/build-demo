package com.innoq.product;

import org.springframework.web.bind.annotation.RestController;

import com.innoq.product.model.Product;
import com.innoq.product.model.ProductDetails;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.innoq.product"})
@RestController
public class ProductsController {

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product fetchProductPrice(@PathVariable final long id) {
        final ProductDetails details = new ProductDetails(id, "details of product " + id);
        return new Product(id, "Foo Product", details);

    }

}