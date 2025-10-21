package com.hrks.OptimaStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.hrks.OptimaStock",
        "domain.typeDocument"
})
@EnableJpaRepositories(basePackages = "com.hrks.OptimaStock.typeDocument.repository")
public class OptimaStockApplication {
    public static void main(String[] args) {
        SpringApplication.run(OptimaStockApplication.class, args);
    }

}
