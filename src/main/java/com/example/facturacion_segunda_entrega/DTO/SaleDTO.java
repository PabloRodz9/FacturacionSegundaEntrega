package com.example.facturacion_segunda_entrega.DTO;

import java.util.Date;
import java.util.Objects;

public class SaleDTO {
    private int clientId;
    private int productId;
    private Integer quantity;
    private Date saleDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleDTO saleDTO = (SaleDTO) o;
        return clientId == saleDTO.clientId && productId == saleDTO.productId && Objects.equals(quantity, saleDTO.quantity) && Objects.equals(saleDate, saleDTO.saleDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, productId, quantity, saleDate);
    }


    @Override
    public String toString() {
        return "SaleDTO{" +
                "clientId=" + clientId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", saleDate=" + saleDate +
                '}';
    }

    // Getters and Setters

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}
