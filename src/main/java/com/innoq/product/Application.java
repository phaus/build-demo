package com.innoq.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "<html><body>"
            + "<h1>Your Products</h1>"
            + "<ul>"
            + "<li><a href=\"/products/1\">product 1</a></li>"
            + "<li><a href=\"/products/2\">product 2</a></li>"
            + "</ul>"
            + "</body></html>";
    }
}
