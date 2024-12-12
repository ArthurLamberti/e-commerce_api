package com.arthurlamberti.ecommerce.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.arthurlamberti.ecommerce")
@EnableTransactionManagement
public class WebServerConfig {
}
