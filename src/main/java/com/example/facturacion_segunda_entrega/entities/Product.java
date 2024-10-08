package com.example.facturacion_segunda_entrega.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Entity representing a product in the system")
@Entity
@Table(name = "products")
public class Product {

    @Schema(description = "Unique identifier of the product", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(description = "Description of the product", example = "Laptop", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Schema(description = "Unique code of the product", example = "PROD123", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Schema(description = "Available stock of the product", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "stock", nullable = false)
    private int stock;

    @Schema(description = "Price of the product", example = "199.99", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "price", nullable = false)
    private double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Sale> sales;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && stock == product.stock && Double.compare(price, product.price) == 0 && Objects.equals(description, product.description) && Objects.equals(code, product.code) && Objects.equals(sales, product.sales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, code, stock, price, sales);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", sales=" + sales +
                '}';
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

}