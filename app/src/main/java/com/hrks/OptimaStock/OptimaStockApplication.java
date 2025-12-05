package com.hrks.OptimaStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
                "com.hrks.OptimaStock",
                "com.hrks.OptimaStock.typeDocument",
                "com.hrks.OptimaStock.typePerson",
                "com.hrks.OptimaStock.category",
                "com.hrks.OptimaStock.person",
                "com.hrks.OptimaStock.iva",
                "com.hrks.OptimaStock.product",
                "com.hrks.OptimaStock.typeMovement",
                "com.hrks.OptimaStock.paymentMethod",
                "com.hrks.OptimaStock.sale",
                "com.hrks.OptimaStock.saleDetail",
                "com.hrks.OptimaStock.user",
                "com.hrks.OptimaStock.inventory",
                "com.hrks.OptimaStock.inventoryMovement"

})
@EntityScan(basePackages = {
                "com.hrks.OptimaStock.typeDocument.model",
                "com.hrks.OptimaStock.typePerson.model",
                "com.hrks.OptimaStock.category.model",
                "com.hrks.OptimaStock.person.model",
                "com.hrks.OptimaStock.iva.model",
                "com.hrks.OptimaStock.product.model",
                "com.hrks.OptimaStock.typeMovement.model",
                "com.hrks.OptimaStock.paymentMethod.model",
                "com.hrks.OptimaStock.sale.model",
                "com.hrks.OptimaStock.saleDetail.model",
                "com.hrks.OptimaStock.user.model",
                "com.hrks.OptimaStock.inventory.model",
                "com.hrks.OptimaStock.inventoryMovement.model"
})
@EnableJpaRepositories(basePackages = "com.hrks.OptimaStock")
public class OptimaStockApplication {
        public static void main(String[] args) {
                SpringApplication.run(OptimaStockApplication.class, args);
        }
}
