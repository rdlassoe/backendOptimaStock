package com.hrks.OptimaStock.category.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory")
    private Long idCategory;

    @Column(name = "descriptionCategory", nullable = false, length = 100)
    private String descriptionCategory;

    public Category() {}

    public Category(String descriptionCategory) {
        this.descriptionCategory = descriptionCategory;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescriptionCategory() {
        return descriptionCategory;
    }

    public void setDescriptionCategory(String descriptionCategory) {
        this.descriptionCategory = descriptionCategory;
    }
}
