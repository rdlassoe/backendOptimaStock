package com.hrks.OptimaStock.product.model;

import com.hrks.OptimaStock.category.model.Category;
import com.hrks.OptimaStock.iva.model.IVA;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "El código del producto es requerido")
    @Size(max = 20, message = "El código no puede exceder 20 caracteres")
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @NotBlank(message = "El nombre del producto es requerido")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Size(max = 100, message = "La descripción no puede exceder 100 caracteres")
    @Column(name = "description", length = 100)
    private String description;

    @NotNull(message = "La categoría es requerida")
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @NotNull(message = "La cantidad mínima es requerida")
    @Min(value = 0, message = "La cantidad mínima no puede ser negativa")
    @Column(name = "min_quantity", nullable = false)
    private Integer minQuantity;

    @Min(value = 0, message = "El costo no puede ser negativo")
    @Column(name = "cost")
    private Integer cost;

    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(name = "price")
    private Integer price;

    @NotNull(message = "El IVA es requerido")
    @ManyToOne
    @JoinColumn(name = "iva_id", referencedColumnName = "idIVA")
    private IVA iva;

    @NotNull(message = "El estado es requerido")
    @Min(value = 0, message = "El estado mínimo es 0")
    @Max(value = 1, message = "El estado máximo es 1")
    @Column(name = "status", nullable = false)
    private Integer status = 1;

    public Product() {
    }

    public Product(String code, String name, String description, Category category,
            Integer minQuantity, Integer cost, Integer price, IVA iva, Integer status) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.category = category;
        this.minQuantity = minQuantity;
        this.cost = cost;
        this.price = price;
        this.iva = iva;
        this.status = status;
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public IVA getIva() {
        return iva;
    }

    public void setIva(IVA iva) {
        this.iva = iva;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
