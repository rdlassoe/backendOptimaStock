package com.hrks.OptimaStock.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer categoryId;
    private Integer minimumStock;
    private BigDecimal cost;
    private BigDecimal price;
    private Integer ivaId;
    private Boolean status;
}
