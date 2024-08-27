package com.example.facturacion_segunda_entrega.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

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