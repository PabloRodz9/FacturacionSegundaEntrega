package com.example.facturacion_segunda_entrega.entities;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Schema(description = "Entity representing a sale transaction in the system")
@Entity
@Table(name = "sales")
public class Sale {

    @Schema(description = "Unique identifier of the sale", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(description = "Client associated with the sale", requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Schema(description = "Product associated with the sale", requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Schema(description = "Quantity of the product sold", example = "5",requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Schema(description = "Date of the sale", example = "2023-08-15", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "sale_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date saleDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return id == sale.id && quantity == sale.quantity && Objects.equals(client, sale.client) && Objects.equals(product, sale.product) && Objects.equals(saleDate, sale.saleDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, product, quantity, saleDate);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", client=" + client +
                ", product=" + product +
                ", quantity=" + quantity +
                ", saleDate=" + saleDate +
                '}';
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

}