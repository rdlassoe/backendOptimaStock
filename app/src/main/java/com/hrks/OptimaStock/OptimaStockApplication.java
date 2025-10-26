package com.hrks.OptimaStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Locale;

@SpringBootApplication(scanBasePackages = {
        "com.hrks.OptimaStock",
        "domain.typeDocument",
        "domain.typePerson",
        "domain.category",
        "domain.person",
        "common"

})
@EnableJpaRepositories(basePackages = {
        "com.hrks.OptimaStock.typeDocument.repository",
        "com.hrks.OptimaStock.typePerson.repository",
        "com.hrks.OptimaStock.category.repository",
        "com.hrks.OptimaStock.person.repository"}
)

public class OptimaStockApplication {
    public static void main(String[] args) {
        SpringApplication.run(OptimaStockApplication.class, args);
    }

}