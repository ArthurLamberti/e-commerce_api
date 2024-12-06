package com.arthurlamberti.ecommerce.infrastructure;

import com.arthurlamberti.ecommerce.infrastructure.config.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//http://localhost:8080/api/swagger-ui/index.html
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(WebServerConfig.class, args);
    }
}