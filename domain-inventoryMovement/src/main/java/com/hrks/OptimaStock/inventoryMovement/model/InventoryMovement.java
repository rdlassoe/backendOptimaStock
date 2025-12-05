package com.hrks.OptimaStock.inventoryMovement.model;

import com.hrks.OptimaStock.inventory.model.Inventory;
import com.hrks.OptimaStock.typeMovement.model.TypeMovement;
import com.hrks.OptimaStock.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_movement")
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "El inventario es requerido")
    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private Inventory inventory;

    @NotNull(message = "La fecha del movimiento es requerida")
    @PastOrPresent(message = "La fecha no puede ser futura")
    @Column(name = "date")
    private LocalDateTime date;

    @NotNull(message = "El tipo de movimiento es requerido")
    @ManyToOne
    @JoinColumn(name = "movement_type_id", referencedColumnName = "idtype_movement")
    private TypeMovement movementType;

    @NotNull(message = "La cantidad es requerida")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Column(name = "quantity")
    private Integer quantity;

    @Size(max = 100, message = "La descripción no puede exceder 100 caracteres")
    @Column(name = "description", length = 100)
    private String description;

    @NotNull(message = "El usuario es requerido")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public InventoryMovement() {
    }

    public InventoryMovement(Inventory inventory, LocalDateTime date, TypeMovement movementType, 
                             Integer quantity, String description, User user) {
        this.inventory = inventory;
        this.date = date;
        this.movementType = movementType;
        this.quantity = quantity;
        this.description = description;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TypeMovement getMovementType() {
        return movementType;
    }

    public void setMovementType(TypeMovement movementType) {
        this.movementType = movementType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
