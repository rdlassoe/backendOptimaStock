package com.hrks.OptimaStock.saleDetail.model;

import com.hrks.OptimaStock.sale.model.Sale;
import com.hrks.OptimaStock.product.model.Product;
import com.hrks.OptimaStock.iva.model.IVA;
import jakarta.persistence.*;

@Entity
@Table(name = "sale_detail")
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "iva_id", referencedColumnName = "idIVA")
    private IVA iva;

    public SaleDetail() {
    }

    public SaleDetail(Sale sale, Product product, Integer quantity, Integer price, IVA iva) {
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.iva = iva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}
